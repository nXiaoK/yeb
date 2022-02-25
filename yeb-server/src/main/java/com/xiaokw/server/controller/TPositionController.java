package com.xiaokw.server.controller;


import com.xiaokw.server.entity.AjaxResult;
import com.xiaokw.server.entity.TPosition;
import com.xiaokw.server.service.ITPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
@Api(tags = "职位管理")
@RestController
@RequestMapping("/system/basic/pos")
public class TPositionController {

    @Autowired
    private ITPositionService positionService;

    @ApiOperation("获取所有职位信息")
    @GetMapping
    public List<TPosition> getAllPositions() {
        return positionService.list();
    }

    @ApiOperation("添加职位信息")
    @PostMapping
    public AjaxResult addPosition(@RequestBody TPosition position) {
        position.setCreateDate(LocalDateTime.now());
        if (positionService.save(position)) {
            return AjaxResult.success("添加成功！");
        }
        return AjaxResult.error("添加失败！");
    }

    @ApiOperation("更新职位信息")
    @PutMapping
    public AjaxResult updatePosition(@RequestBody TPosition position) {
        if (positionService.updateById(position)) {
            return AjaxResult.success("更新成功！");
        }
        return AjaxResult.error("更新失败！");
    }

    @ApiOperation("删除职位信息")
    @DeleteMapping("/{id}")
    public AjaxResult deletePosition(@PathVariable Integer id) {
        if (positionService.removeById(id)) {
            return AjaxResult.success("删除成功！");
        }
        return AjaxResult.error("删除失败！");
    }

    @ApiOperation("批量删除职位信息")
    @DeleteMapping
    public AjaxResult deletePositionByIds(Integer[] ids) {
        if (positionService.removeByIds(Arrays.asList(ids))) {
            return AjaxResult.success("删除成功！");
        }
        return AjaxResult.error("删除失败！");
    }
}

