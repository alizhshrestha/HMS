package com.example.reportservice.util;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ExcelFileWriter {

//    public static <T> XSSFWorkbook writeToExcel(HttpServletResponse response, String fileName, List<T> data) {
//        ServletOutputStream ops = null;
//        XSSFWorkbook workbook = null;
//        try {
//            File file = new File(fileName);
//            workbook = new XSSFWorkbook();
//            Sheet sheet = workbook.createSheet();
//            List<String> fieldNames = getFieldNamesForClass(data.get(0).getClass());
//            int rowCount = 0;
//            int columnCount = 0;
//            Row row = sheet.createRow(rowCount++);
//            for (String fieldName : fieldNames) {
//                Cell cell = row.createCell(columnCount++);
//                cell.setCellValue(fieldName);
//            }
//            Class<? extends Object> classz = data.get(0).getClass();
//            for (T t : data) {
//                row = sheet.createRow(rowCount++);
//                columnCount = 0;
//                for (String fieldName : fieldNames) {
//                    Cell cell = row.createCell(columnCount);
//                    Method method = null;
//                    try {
//                        method = classz.getMethod("get" + capitalize(fieldName));
//                    } catch (NoSuchMethodException nme) {
//                        method = classz.getMethod("get" + fieldName);
//                    }
//                    Object value = method.invoke(t, (Object[]) null);
//                    if (value != null) {
//                        if (value instanceof String) {
//                            cell.setCellValue((String) value);
//                        } else if (value instanceof Long) {
//                            cell.setCellValue((Long) value);
//                        } else if (value instanceof Integer) {
//                            cell.setCellValue((Integer) value);
//                        } else if (value instanceof Double) {
//                            cell.setCellValue((Double) value);
//                        }
//                    }
//                    columnCount++;
//                }
//            }
//            ops = response.getOutputStream();
//            workbook.write(ops);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//
//                if (ops != null) {
//                    ops.close();
//                }
//            } catch (IOException e) {
//            }
//            try {
//                if (workbook != null) {
//                    workbook.close();
//                }
//            } catch (IOException e) {
//            }
//        }
//
//        return workbook;
//    }
//
//    // retrieve field names from a POJO class
//    private static List<String> getFieldNamesForClass(Class<?> clazz) throws Exception {
//        List<String> fieldNames = new ArrayList<String>();
//        Field[] fields = clazz.getDeclaredFields();
//        for (int i = 0; i < fields.length; i++) {
//            fieldNames.add(fields[i].getName());
//        }
//        return fieldNames;
//    }
//
//    // capitalize the first letter of the field name for retriving value of the
//    // field later
//    private static String capitalize(String s) {
//        if (s.length() == 0)
//            return s;
//        return s.substring(0, 1).toUpperCase() + s.substring(1);
//    }


    public static XSSFWorkbook writeToExcel(HttpServletResponse response, String fileName, List<Map<String, Object>> data){

        ServletOutputStream ops = null;
        XSSFWorkbook workbook = null;

        try {
            File file = new File(fileName);
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();

            int rowCount = 0;
            int columnCount = 0;
            Row row = sheet.createRow(rowCount++);

            Map<String, Object> setupData = data.get(0);

            //getting field names from list of map
            List<String> fieldNames = new ArrayList<>();
            setupData.forEach((k, v) -> {
                fieldNames.add(k);
            });

            //creating cell for title row
            for (String fieldName : fieldNames) {
                Cell cell = row.createCell(columnCount++);
                cell.setCellValue(fieldName);
            }


            for (Map<String, Object> d : data) {
                row = sheet.createRow(rowCount++);
                columnCount = 0;

                for (String fieldName : fieldNames) {
                    Cell cell = row.createCell(columnCount);
                    if (d.get(fieldName) != null){
                        String value = d.get(fieldName).toString();
                        cell.setCellValue(value);
                    }

                    columnCount++;
                }
            }

            ops = response.getOutputStream();
            workbook.write(ops);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (ops != null) {
                    ops.close();
                }
            } catch (IOException e) {
            }
            try {
                if (workbook != null) {
                    workbook.close();
                }
            } catch (IOException e) {
            }
        }

        return workbook;

    }
}
