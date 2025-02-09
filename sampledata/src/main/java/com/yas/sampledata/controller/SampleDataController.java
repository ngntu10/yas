package com.yas.sampledata.controller;

import com.yas.sampledata.service.SampleDataService;
import com.yas.sampledata.viewmodel.ErrorVm;
import com.yas.sampledata.viewmodel.SampleDataVm;
import com.yas.sampledata.viewmodel.SampleTypeVM;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Validated
@RestController
public class SampleDataController {
    private final SampleDataService sampleDataService;

    public SampleDataController(SampleDataService sampleDataService) {
        this.sampleDataService = sampleDataService;
    }

    @PostMapping(path = "/storefront/sampledata")
    @Operation(summary = "Add product to shopping sampleData. When no sampleData exists, this will create a new sampleData.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = SampleDataVm.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
    public SampleDataVm createSampleData(@RequestBody SampleTypeVM sampleTypeVM) throws SQLException {
        return sampleDataService.addSampleData(sampleTypeVM);
    }
}
