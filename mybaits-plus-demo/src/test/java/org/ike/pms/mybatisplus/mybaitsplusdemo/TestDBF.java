package org.ike.pms.mybatisplus.mybaitsplusdemo;

import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class TestDBF {
    public static void main(String[] args) {
        String path = "F:\\test.dbf";
        writeDBF(path);
    }


    public static void writeDBF(String path) {


        OutputStream fos = null;

        try {

            //定义DBF文件字段

            DBFField[] fields = new DBFField[3];

            //分别定义各个字段信息，setFieldName和setName作用相同，

            //只是setFieldName已经不建议使用

            fields[0] = new DBFField();

            //fields[0].setFieldName("emp_code");

            fields[0].setName("semp_code");

            fields[0].setDataType(DBFField.FIELD_TYPE_C);

            fields[0].setFieldLength(10);


            fields[1] = new DBFField();

            //fields[1].setFieldName("emp_name");

            fields[1].setName("emp_name");

            fields[1].setDataType(DBFField.FIELD_TYPE_C);

            fields[1].setFieldLength(20);


            fields[2] = new DBFField();

            //fields[2].setFieldName("salary");

            fields[2].setName("salary");

            fields[2].setDataType(DBFField.FIELD_TYPE_N);

            fields[2].setFieldLength(12);

            fields[2].setDecimalCount(2);


            //DBFWriter writer = new DBFWriter(new File(path));

            //定义DBFWriter实例用来写DBF文件

            DBFWriter writer = new DBFWriter();

            //把字段信息写入DBFWriter实例，即定义表结构

            writer.setFields(fields);

            //一条条的写入记录

            Object[] rowData = new Object[3];

            rowData[0] = "1000";

            rowData[1] = "John";

            rowData[2] = new Double(5000.00);

            writer.addRecord(rowData);

            rowData = new Object[3];

            rowData[0] = "1001";

            rowData[1] = "Lalit";

            rowData[2] = new Double(3400.00);

            writer.addRecord(rowData);

            rowData = new Object[3];

            rowData[0] = "1002";

            rowData[1] = "Rohit";

            rowData[2] = new Double(7350.00);

            writer.addRecord(rowData);

            //定义输出流，并关联的一个文件

            fos = new FileOutputStream(path);

            //写入数据

            writer.write(fos);

            //writer.write();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                fos.close();

            } catch (Exception e) {
            }

        }

    }


}
