public class Parquet {



 public void calculate(int N, int M){
     int A [][] = new int [N][M];
     int B [][] = new int [256][M+1];
     int max;
     for (int i = 1; i <= N; i++) {
         //max = Math.pow(2, i+1)-1;  // 2Schi-1
         //max = st2(i+1) - 1;
         max = (2 << i) - 1;
         // fillChar
         B[0][0] = 1;
         for (int j = 1; j <= M ; j++) {
             if((i*j) % 2 == 0) { // если результат произведения i*j четное
                 if(((i == 1) && (j % 2 == 0)) || ((j == 1) && (i % 2 == 0))){
                     A[i-1][j-1] = 1;
                 } else {
                     for (int k = 0; k <= max; k++) { // Сечение с номером k
                         for (int l = 0; l <= max; l++) { // Сечение с номером l
                             if (acceptCut(k, l, i)) {
                                 B[k][j] += B[l][j - 1];

                             }
                         }
                     }
                     A[i - 1][j - 1] = B[0][j];
                 }
             } else {
                 A[i-1][j-1] = 0;
             }
         }
     }
     showResult(A);
 }

    // метод для сопоставления сечений
    public static boolean acceptCut(int firstCut, int secondCut, int digitsCount){
        int countSecond = 0;// переменная для хранения кол-ва 0 во втором сечении

        for(int i=0;i<digitsCount;i++){//цикл по каждому разряду сечения
            int digit = (1<<i);// маска
            if((firstCut&digit)==0) {//если i-ый разряд 1-го сечения =0
                if ((secondCut & digit) == 0) {//если i-ый разряд 2-го сечения =0
                    countSecond++;
                } else {//если i-ый разряд 2-го сечения !=0
                    if(countSecond%2!=0) return false;// и кол-во 0 во втором сечении - нечетно
                }
            } else {//если i-ый разряд 1-го сечения !=0
                if ((secondCut & digit)!=0) return false; // (countSecond%2!=0)|| и кол-во 0 во втором сечении - нечетно
            }
        }
        if((countSecond%2!=0)) return false;//(countFirst%2!=0)||
        return true;
    }

    // Проверяется совместимость сечений
    // k,l -номера сечений, pi -количество анализируемых разрядов сечений
    public boolean can(int k, int l, int pi){
        boolean b = false;
        boolean result = false;
        int d;
        for (int i = 1; i <= pi; i++) {
            d =  2 << i;
            if ((k & d) == 0){ // Определяется значение разряда с номером d для сечения k
                if((l & d) == 0){
                    b = !b;
                } else {
                    if(b){
                        return result;
                    }
                }
            } else {
                if(((l & d) != 0)|| b) {
                    return result;
                }
            }
        }
        result = !b;
        return result;
    }


    private void showResult(int values[][]){
        for (int arr[] :
                values ) {
            for (int value :
                    arr) {
                System.out.printf("%12d", value);
            }
            System.out.println("\n");
        }
    }


}