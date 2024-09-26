package org.example.testtask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.testtask.service.ExcelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/excel")
@Tag(name = "Excel API", description = "Поиск N-ого максимального числа из excel файла")
public class ExcelController {

    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("/max/{n}")
    @Operation(summary = "Метод поиска N-ого максимального числа из excel файла")
    public Integer getNthMax(@Parameter(description = "Путь к excel файлу") @RequestParam String filePath,
                             @Parameter(description = "Число N (номер максимального числа)") @PathVariable int n) {
        return excelService.getNthMax(filePath, n);
    }
}