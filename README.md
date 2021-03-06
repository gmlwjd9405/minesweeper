## Minesweeper

### Requirement
- 기본적인 **지뢰 찾기** 로직 구현  
    - 기본 사각형은 가로 10줄, 세로 10줄
    - 지뢰는 가급적 랜덤에 가깝게 배치
    - 각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 갯수
- 가급적 코드의 깊이(indent)를 얕게 구현할 것
- else 예약어 사용을 최소화할 것 (삼항 연산자 및 switch 등 분기 연산자 포함)
- 함수는 가급적 작은 기능을 가지도록 할 것
    
### Troubleshooting Strategy
- **지뢰 랜덤 배치**
    - 문제점: 랜덤 함수 반복 문제
        - 시간이 지날수록 비어있는 칸의 수가 적어지므로 충돌 횟수가 늘어난다.
        - 따라서, 랜덤 함수를 반복 수행할 확률이 높아진다.
    - 해결책 
        1. 조건에 맞는 랜덤 함수의 범위만큼의 배열 생성 
        2. 인덱스 값을 할당 
        3. 배열의 값을 랜덤 정렬 
        4. 배열에서 생성할 지뢰의 갯수만큼 값을 추출 
- **지뢰의 갯수 출력** 
    - 모든 칸을 돌면서 자신을 제외한 주변 8개 칸에 포함된 지뢰의 갯수 구하기 
    - 문제점: 같은 칸에 대한 반복 작업 
        - 10*10 칸의 경우: 800번(모든 칸의 갯수 * 8칸) 수행
    - 해결책 1): "캐시"
        - 이미 처리한 부분은 저장한 결과값을 사용 (일반적인 해결책)
    - 해결책 2): 지뢰가 있는 위치의 주변 8개 사각형의 갯수 증가하기
        - 10*10 칸의 경우: 80번(지뢰의 갯수 * 8칸) 수행