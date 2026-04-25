package Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataprovider
{

    @DataProvider(name = "data")
    public String[][] getdata() throws IOException
    {
        String path = System.getProperty("user.dir") + "\\testdata\\Opencart_LoginData.xlsx";

        ExcelUtility ex = new ExcelUtility(path);

        int total_rows = ex.getrowcount("Sheet1");
        int total_cells = ex.getcellcount("Sheet1", 1);

        String logindata[][] = new String[total_rows][total_cells];

        for (int i = 1; i <= total_rows; i++)
        {
            for (int j = 0; j < total_cells; j++)
            {
                logindata[i - 1][j] = ex.getcelldata("Sheet1", i, j);
            }
        }

        return logindata;
    }
}