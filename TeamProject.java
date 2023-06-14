package sparta.week02;

import java.util.*;

public class TeamProject {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> intComInput = computer();//computer()메소드로 부터 중복되지 않는 난수 3개로 이루어진 arraylist생성

        System.out.println("컴퓨터가 숫자를 생성하였습니다.");
        int number = 1;
        int ball;
        int strike;

        while(true){
            System.out.print(number + "번째 시도 : ");
            ball = 0;
            strike = 0;

            String StringUserInput = sc.nextLine();//유저 입력받은 후 배열로 초기화
            int[] intUserArr = new int[3];
            for(int i = 0 ; i < 3; i++){
                intUserArr[i] = Integer.parseInt(String.valueOf(StringUserInput.charAt(i)));
            }

            for(int i = 0 ; i < intUserArr.length; i++){//두 배열을 비교하며 만약 숫자가 같을 때 두 배열의 인덱스도 같아면 strike 다르아면 ball
                for(int j = 0 ; j < intComInput.size(); j++){
                    if(intUserArr[i] == intComInput.get(j)){  //1 2 3 i
                        if(i == j){                           //1 2 3 j
                            strike++;
                        }else{
                            ball++;
                        }
                    }
                }
            }
            if(ball == 0 && strike == 0){
                System.out.println(ball + "B" + strike + "S");
            }else if(ball == 0 && strike != 0){
                System.out.println(strike + "S");
            }else if(strike == 0){
                System.out.println(ball + "B");
            }else{
                System.out.println(ball + "B" + strike + "S");
            }

            if(strike == 3){//3strike -> 종료
                System.out.println(number - 1 + "번만에 맞히셨습니다.");
                break;
            }

            number++;
        }
    }

    public static ArrayList<Integer> computer(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        while(arrayList.size() < 3){
            int number = (int)(Math.random()*10);
            if(arrayList.contains(number))continue;
            arrayList.add(number);
        }
        return arrayList;
    }
}