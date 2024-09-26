package org.example.testtask.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.PriorityQueue;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.testtask.exception.FileProcessingException;
import org.springframework.stereotype.Service;

@Service
public class ExcelService {

    public Integer getNthMax(String filePath, int n) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(n);

        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            var sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                int number = (int) row.getCell(0).getNumericCellValue();
                if (minHeap.size() < n) {
                    minHeap.offer(number);
                } else {
                    Integer min = minHeap.peek();
                    if (min != null && number > min) {
                        minHeap.poll();
                        minHeap.offer(number);
                    }
                }
            }
        } catch (IOException e) {
            throw new FileProcessingException("Ошибка при обработке файла: " + e.getMessage());
        }

        if (minHeap.isEmpty()) {
            throw new FileProcessingException("Файл не содержит чисел.");
        }

        return minHeap.peek();
    }
}