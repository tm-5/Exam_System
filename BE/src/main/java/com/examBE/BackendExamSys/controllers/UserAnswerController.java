package com.examBE.BackendExamSys.controllers;

import com.examBE.BackendExamSys.entities.UserAnswerEntity;
import com.examBE.BackendExamSys.entities.UserAnswerEntity;
import com.examBE.BackendExamSys.models.ApiResult;
import com.examBE.BackendExamSys.models.Dto.UserAnswerDto;
import com.examBE.BackendExamSys.models.UserAnswerDetailModel;
import com.examBE.BackendExamSys.services.UserAnswerService;
import com.examBE.BackendExamSys.utils.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/userAnswers")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserAnswerController {
    private final UserAnswerService userAnswerService;

    @GetMapping("/list")
    public ResponseEntity<ApiResult<List<UserAnswerEntity>>> showList() {
        List<UserAnswerEntity> userList = userAnswerService.getAll();
        String message = "Success";
        if (userList.isEmpty()) {
            message = "Empty";
        }
        ApiResult<List<UserAnswerEntity>> result = ApiResult.create(HttpStatus.OK, message, userList);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/searchById")
    public ResponseEntity<ApiResult<UserAnswerEntity>> searchById(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        ApiResult<UserAnswerEntity> result = null;
        try{
            UserAnswerEntity exam = userAnswerService.searchById(id);
            result = ApiResult.create(HttpStatus.OK, Constants.SEARCH_DATA_SUCCESS, exam);
        }
        catch (Exception ex) {
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/searchDetailByIdContest")
    public ResponseEntity<ApiResult<UserAnswerDetailModel>> searchDetailByIdUser(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        ApiResult<UserAnswerDetailModel> result = null;
        try{
            UserAnswerDetailModel exam = userAnswerService.searchDetailByIdContest(id);
            result = ApiResult.create(HttpStatus.OK, Constants.SEARCH_DATA_SUCCESS, exam);
        }
        catch (Exception ex) {
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), null);
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping(path = "/start")
    public ResponseEntity<ApiResult<UserAnswerEntity>> start(@RequestBody UserAnswerEntity userAnswerEntity){
            ApiResult<UserAnswerEntity> result = null;
            try{
                userAnswerService.start(userAnswerEntity);
                result = ApiResult.create(HttpStatus.OK, Constants.SEARCH_DATA_SUCCESS, null);
            }
            catch (Exception ex) {
                result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), null);
            }
            return ResponseEntity.ok(result);
    }
    @PostMapping(path = "/submit")
    public ResponseEntity<ApiResult<UserAnswerDto>> submit(@RequestBody UserAnswerDto userAnswerDto){
        ApiResult<UserAnswerDto> result = null;
        try{
            userAnswerService.submit(userAnswerDto);
            result = ApiResult.create(HttpStatus.OK, Constants.SEARCH_DATA_SUCCESS, userAnswerDto);
        }
        catch (Exception ex) {
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), userAnswerDto);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ApiResult<UserAnswerEntity>> edit(@Valid @RequestBody UserAnswerEntity userAnswerEntity) {
        ApiResult<UserAnswerEntity> result = null;
        try{
            userAnswerService.edit(userAnswerEntity);
            result = ApiResult.create(HttpStatus.OK, Constants.EDIT_DATA_SUCCESS, userAnswerEntity);
        }
        catch (Exception ex){
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), null);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<ApiResult<Integer>> delete(@RequestParam(name = "id", required = false, defaultValue = "") int id){
        ApiResult<Integer> result = null;
        try{
            userAnswerService.delete(id);
            result = ApiResult.create(HttpStatus.OK, Constants.DELETE_DATA_SUCCESS, id);
        }
        catch (Exception ex) {
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), id);
        }
        return ResponseEntity.ok(result);
    }
}
