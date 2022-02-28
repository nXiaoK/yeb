package com.xiaokw.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.xiaokw.server.entity.*;
import com.xiaokw.server.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
@Api(tags = "员工管理")
@RestController
@RequestMapping("/employee/basic")
public class TEmployeeController {

    @Autowired
    private ITEmployeeService employeeService;
    @Autowired
    private ITPoliticsStatusService politicsStatusService;
    @Autowired
    private ITPositionService positionService;
    @Autowired
    private ITJoblevelService joblevelService;
    @Autowired
    private ITNationService nationService;
    @Autowired
    private ITDepartmentService departmentService;

    @ApiOperation("获取所有员工(分页)")
    @GetMapping
    public AjaxResultPage getEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                      @RequestParam(defaultValue = "10") Integer size,
                                      TEmployee employee, LocalDate[] beginDateScope) {
        return employeeService.getEmployeeByPage(currentPage, size, employee, beginDateScope);
    }

    @ApiOperation("获取所有政治面貌")
    @GetMapping("/politicsStatus")
    public List<TPoliticsStatus> getAllPoliticsStatus() {
        return politicsStatusService.list();
    }

    @ApiOperation("获取所有职位")
    @GetMapping("/positions")
    public List<TPosition> getAllPositions() {
        return positionService.list();
    }

    @ApiOperation("获取所有民族")
    @GetMapping("/nations")
    public List<TNation> getAllNations() {
        return nationService.list();
    }

    @ApiOperation("获取所有职称")
    @GetMapping("/joblevels")
    public List<TJoblevel> getAllJoblevels() {
        return joblevelService.list();
    }

    @ApiOperation("获取所有部门")
    @GetMapping("/departments")
    public List<TDepartment> getAllDepartments() {
        return departmentService.getAllDepartment();
    }

    @ApiOperation("获取工号")
    @GetMapping("/maxWorkID")
    public AjaxResult getMaxWorkID() {
        return employeeService.getMaxWorkID();
    }

    @ApiOperation("添加员工")
    @PostMapping
    public AjaxResult addEmp(@RequestBody TEmployee employee) {
        return employeeService.addEmp(employee);
    }

    @ApiOperation("更新员工")
    @PutMapping
    public AjaxResult updateDep(@RequestBody TEmployee employee) {
        if (employeeService.updateById(employee)) {
            return AjaxResult.success("更新成功");
        }
        return AjaxResult.error("更新失败");
    }

    @ApiOperation("删除员工")
    @DeleteMapping("/{id}")
    public AjaxResult deleteDep(@PathVariable Integer id) {
        if (employeeService.removeById(id)) {
            return AjaxResult.success("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @ApiOperation(value = "导出员工数据", produces = "application/octet-stream")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void export(HttpServletResponse response) {
        List<TEmployee> employees = employeeService.getAllEmployee();
        Workbook sheets = ExcelExportUtil.exportExcel(new ExportParams("员工表", "员工表"), TEmployee.class, employees);
        ServletOutputStream outputStream = null;
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("员工表.xls", "UTF-8"));
            outputStream = response.getOutputStream();
            sheets.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @ApiOperation("查询所有员工")
    @GetMapping("/allEmp")
    public List<TEmployee> getAllEmp() {
        return employeeService.getAllEmployee();
    }

    @ApiOperation("导入员工数据")
    @PostMapping("/import")
    public AjaxResult importEmp(@RequestPart @RequestParam MultipartFile file) {
        ImportParams importParams = new ImportParams();
        List<TNation> nationList = nationService.list();
        List<TPoliticsStatus> politicsStatusList = politicsStatusService.list();
        List<TPosition> positionList = positionService.list();
        List<TJoblevel> joblevelList = joblevelService.list();
        List<TDepartment> departmentList = departmentService.list();
        // 表示去除标题所占行数
        importParams.setTitleRows(1);
        try {
            List<TEmployee> list = ExcelImportUtil.importExcel(file.getInputStream(), TEmployee.class, importParams);
            // 下面是一个 查找各个对象id的过程
            list.forEach(employee -> {
                // 新建对象
                TNation tNation = new TNation(employee.getNation().getName());
                // 因为TNation实体类里重写了equals 并且是使用name来判断  所以这里可以使用此方法查询到
                int i = nationList.indexOf(tNation);
                // 拿到name名字对应id
                Integer id = nationList.get(i).getId();
                // 设置民族对应id
                employee.setNationId(id);
                // 下面就采用嵌套的写法
                employee.setPoliticId(politicsStatusList.get(politicsStatusList.indexOf(new TPoliticsStatus(employee.getPoliticsStatus().getName()))).getId());
                employee.setPosId(positionList.get(positionList.indexOf(new TPosition(employee.getPosition().getName()))).getId());
                employee.setJobLevelId(joblevelList.get(joblevelList.indexOf(new TJoblevel(employee.getJoblevel().getName()))).getId());
                employee.setDepartmentId(departmentList.get(departmentList.indexOf(new TDepartment(employee.getDepartment().getName()))).getId());

            });
            if (employeeService.saveBatch(list)) {
                return AjaxResult.success("导入成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.error("导入失败");
    }
}

