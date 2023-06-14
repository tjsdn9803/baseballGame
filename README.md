# 야구게임
팀원: 이선우, 이예원

----

## 야구게임 룰
* 컴퓨터는 0과 9 사이의 서로 다른 숫자 3개를 무작위로 뽑습니다.
* 사용자는 컴퓨터가 뽑은 숫자를 맞추기 위해 시도합니다.
* 컴퓨터는 사용자가 입력한 세자리 숫자에 대해서, 아래의 규칙대로 스트라이크와 볼을 알려줍니다.
  * 숫자의 값과 위치가 모두 일치하면 S
  * 숫자의 값은 일치하지만 위치가 틀렸으면 B
* 기회는 무제한이며, 몇번의 시도 후에 맞췄는지 기록됩니다.
* 숫자 3개를 모두 맞춘 경우, 게임을 종료합니다.

>진행방식

```
컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!
1번째 시도 : 134
0B0S
2번째 시도 : 238
1B1S
3번째 시도 : 820
2B1S
4번째 시도 : 028
3B
5번째 시도 : 280
3S
4번만에 맞히셨습니다. 
게임을 종료합니다.
```
-----
## 주요 코드 부분 설명

```java
public static ArrayList<Integer> computer(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        while(arrayList.size() < 3){
            int number = (int)(Math.random()*10);
            if(arrayList.contains(number))continue;
            arrayList.add(number);
        }
        return arrayList;
    }
```
컴퓨터가 랜덤한 숫자 세개를 생성하는 메소드입니다.

처음 중복제거 키워드를 보고 Set자료구조를 생각하였으나 Set.add()시 숫자가 정렬되는 현상을 발견하였습니다.

게임의 룰 중 단순히 숫자를 맞추는것 뿐 아니라 같은 순번일때 일치하느냐가 중요한 요소이기 때문에 다른 방안을 생각해보았고
배열과 ArrayList중 가변적 길이를 가질 수 있는 ArrayList를 선택하였습니다.

랜덤한 수 3개를 뽑는 과정은 우선 10미만의 랜덤한 수를  Math.random()으로 뽑고 ArrayList에 존재하지 않는 값이라면
중복이 아닌 경우이므로 ArrayList에 값을 넣고 있다면 다시 다른 수를 뽑아 과정을 반복합니다.

```java
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
```
생략된 과정에서 사용자가 입력한 세개의 숫자가 담긴 배열과 컴퓨터가 생성해낸 랜덤한 세개의 수가 담긴 ArrayList배열을 비교하는 반복문입니다.

이중 for문 을 사용하여 배열과 ArrayList에 담긴 값을 비교하는데 만약 비교했을 때 값이 같다면 Ball 아니면 Strike이므로 조건문을 작성하였고
값이 같을 때 두 배열의 인덱스가 같다면 야구게임 룰의 Strike에 해당하므로 strike를 증가시키고 값은 같지만 인덱스는 다르다면 Ball에 해당하므로 ball을 증가시킵니다.

-----

맞추는 과정은 while(true)를 통해 무한반복하게 되는데 야구게임은 3Strike면 게임을 종료하기 때문에 strike가 3이라면 break를 통홰 반복문을 탈출합니다.



## 전체 코드

-------

```java
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
```

-----
## 새로 알게 된 점
처음 Set를 사용했을 때 정렬된 값이 나와 사용하지 못했다. 그래서 Set에 Set.add()가 값을 저장하는 방식에 대해 알아본 결과 Set에 처음 add()시 Set는 16으로 resize()하게 되는데 만약 1을 저장하려한다면 1번째 노드에 저장하게 되고 3을 저장하게 되면 3번째 노드에 저장하게 된다.
여기서 값을 출력하게 되면 노드순으로 출력되기 때문에 정렬된 값을 받게 되었던 것이다. 그리고 만약 17을 저장하게 된다면 노드의 크기 16을 넘어가기 때문에 첫번째 노드의 2번째 값으로 저장되게 되어 만약 1,3,17,을 add()하게 되면 [1,17,3]으로 출력되게 된다.

Set의 저장방식에 대해 자세히는 아니지만 어느정도 매커니즘을 알게되었고 야구게임같이 중복제거도 중요하지만 저장되는 순서가 중요한 문제의 경우 Set자료구조의 사용은 자제 해야겠다는 것을 알게되었다. 

