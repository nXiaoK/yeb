package com.xiaokw.server.controller;


import com.xiaokw.server.entity.AjaxResult;
import com.xiaokw.server.entity.TJoblevel;
import com.xiaokw.server.service.ITJoblevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
@Api(tags = "职称管理")
@RestController
@RequestMapping("/system/basic/joblevel")
public class TJoblevelController {
    @Autowired
    private ITJoblevelService joblevelService;

    @ApiOperation("获取所有职称")
    @GetMapping
    public List<TJoblevel> getAllJobLevels() {
        return joblevelService.list();
    }

    @ApiOperation("添加职称")
    @PostMapping
    public AjaxResult addJobLevel(@RequestBody TJoblevel joblevel) {
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)) {
            return AjaxResult.success("添加成功");
        }
        return AjaxResult.error("添加失败");
    }


    @ApiOperation("更新职称")
    @PutMapping
    public AjaxResult updateJobLevel(@RequestBody TJoblevel joblevel) {
        if (joblevelService.updateById(joblevel)) {
            return AjaxResult.success("更新成功");
        }
        return AjaxResult.error("更新失败");
    }

    @ApiOperation("删除职称")
    @DeleteMapping("/{id}")
    public AjaxResult deleteJobLevel(@PathVariable Integer id) {
        if (joblevelService.removeById(id)) {
            return AjaxResult.success("删除成功");
        }
        return AjaxResult.error("删除失败");
    }


    @ApiOperation("批量删除职称")
    @DeleteMapping
    public AjaxResult deleteJobLevelByIds(Integer... ids) {
        if (joblevelService.removeByIds(Arrays.asList(ids))) {
            return AjaxResult.success("删除成功");
        }
        return AjaxResult.error("删除失败");
    }
}

