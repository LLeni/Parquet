import org.junit.Before;
import org.junit.Test;
import org.junit.*;

import static org.junit.Assert.*;

public class ParquetTest {
    Parquet parquet;
    @Before
    public void init(){
        parquet = new Parquet();
    }

    /*
     l = 1; k = 0; b = false -> false
     l = 1; k = 0; b = true -> несовместимы
     l = 0; k = 1; b = false -> false
     l = 0; k = 1; b = true -> несовместимы
     l = 0; k = 0; b = false -> true
     l = 0; k = 0; b = true -> false
     l = 1; k = 1; b = false -> несовместимы
     */
    @Test
    public void testCan(){
        //                l k b result. 2 у result -> несовместимы
        int cases[][] = {{1,0,0,0     }, {1,0,1,2}, {0,1,0,0}, {0,1,1,2}, {0,0,0,1}, {0,0,1,0}, {1,1,0,2}};
        for (int i = 0; i < cases.length; i++) {
            boolean expectResult;
            if(cases[i][3] == 1){
                expectResult = true;
            } else {
                expectResult = false;
            }
            assertEquals("При l = " + cases[i][0] + " и k = " + cases[i][1] + " посчитан неожидаемый результат" , expectResult, parquet.can(cases[i][1], cases[i][0],1));
        }
    }
}
