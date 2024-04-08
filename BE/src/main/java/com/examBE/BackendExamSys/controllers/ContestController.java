package com.examBE.BackendExamSys.controllers;

import com.examBE.BackendExamSys.entities.ContestEntity;
import com.examBE.BackendExamSys.models.ApiResult;
import com.examBE.BackendExamSys.models.ContestExamModel;
import com.examBE.BackendExamSys.models.ContestUserModel;
import com.examBE.BackendExamSys.models.Dto.ContestDto;
import com.examBE.BackendExamSys.models.ContestDetailModel;
import com.examBE.BackendExamSys.services.ContestService;
import com.examBE.BackendExamSys.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/contests")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ContestController {
    private final ContestService contestService;

    @GetMapping("/list")//lay tat ca ds contest
    public ResponseEntity<ApiResult<List<ContestEntity>>> showList() {
        List<ContestEntity> userList = contestService.getAll();
        String message = "Success";
        if (userList.isEmpty()) {
            message = "Empty";
        }
        //goi du lieu vao class ApiResult gom co status, message va data
        ApiResult<List<ContestEntity>> result = ApiResult.create(HttpStatus.OK, message, userList);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/searchById")
    public ResponseEntity<ApiResult<ContestEntity>> searchById(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        ApiResult<ContestEntity> result = null;
        try{
            ContestEntity exam = contestService.searchById(id);
            result = ApiResult.create(HttpStatus.OK, Constants.SEARCH_DATA_SUCCESS, exam);
        }
        catch (Exception ex) {
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), null);
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping(path = "/searchDetailById")
    public ResponseEntity<ApiResult<ContestDetailModel>> searchDetailById(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        ApiResult<ContestDetailModel> result = null;
        try{
            ContestDetailModel exam = contestService.searchDetailById(id);
            result = ApiResult.create(HttpStatus.OK, Constants.SEARCH_DATA_SUCCESS, exam);
        }
        catch (Exception ex) {
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), null);
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping(path = "/searchDetailByIdUser")
    public ResponseEntity<ApiResult<List<ContestDetailModel>>> searchDetailByIdUser(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        ApiResult<List<ContestDetailModel>> result = null;
        try{
            List<ContestDetailModel> exam = contestService.searchDetailByIdUser(id);
            result = ApiResult.create(HttpStatus.OK, Constants.SEARCH_DATA_SUCCESS, exam);
        }
        catch (Exception ex) {
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), null);
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping(path = "/create")
    public ResponseEntity<ApiResult<ContestDto>> create(@RequestBody ContestDto contestDto){
        ApiResult<ContestDto> result = null;
        try{
            contestService.create(contestDto);
            result = ApiResult.create(HttpStatus.OK, Constants.SAVE_DATA_SUCCESS, contestDto);
        }
        catch (Exception ex) {
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), contestDto);
        }
        return ResponseEntity.ok(result);
    }
    @PutMapping(path = "/update")
    public ResponseEntity<ApiResult<ContestEntity>> update(@RequestBody ContestEntity contestEntity){
        ApiResult<ContestEntity> result = null;
        try{
            contestService.edit(contestEntity);
            result = ApiResult.create(HttpStatus.OK, Constants.EDIT_DATA_SUCCESS, contestEntity);
        }
        catch (Exception ex) {
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), contestEntity);
        }
        return ResponseEntity.ok(result);
    }
    @DeleteMapping(path = "/delete")
    public ResponseEntity<ApiResult<Integer>> delete(@RequestParam(name = "id", required = false, defaultValue = "") int id){
        ApiResult<Integer> result = null;
        try{
            contestService.delete(id);
            result = ApiResult.create(HttpStatus.OK, Constants.DELETE_DATA_SUCCESS, id);
        }
        catch (Exception ex) {
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), id);
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/constestUserStatistic")
    public ResponseEntity<ApiResult<List<ContestUserModel>>> contestUser() {
        List<ContestUserModel> rs = contestService.findWithUser();
        String message = "Success";
        if (rs.isEmpty()) {
            message = "Empty";
        }
        ApiResult<List<ContestUserModel>> result = ApiResult.create(HttpStatus.OK, message, rs);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/constestExamStatistic")
    public ResponseEntity<ApiResult<List<ContestExamModel>>> contestExam() {
        List<ContestExamModel> rs = contestService.findWithExam();
        String message = "Success";
        if (rs.isEmpty()) {
            message = "Empty";
        }
        ApiResult<List<ContestExamModel>> result = ApiResult.create(HttpStatus.OK, message, rs);
        return ResponseEntity.ok(result);
    }
}
