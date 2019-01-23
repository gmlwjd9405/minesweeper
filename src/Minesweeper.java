import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Minesweeper {
    private static final int ROW = 10;
    private static final int COL = 10;
    private static final int THE_NUM_OF_MINE = 10;
    private static final char MINE = '*';
    private static final char NONE = '0';

    private char[][] map;

    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
        minesweeper.initMap();
        minesweeper.createMine(THE_NUM_OF_MINE);
        minesweeper.setCntOfMine();
        minesweeper.printMap();
    }

    /* default 생성자 */
    public Minesweeper() {
        map = new char[ROW][COL];
    }

    /* map 초기화 */
    private void initMap() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                map[i][j] = NONE;
            }
        }
    }

    /* 지뢰 위치 랜덤 생성 */
    private void createMine(int mineCount) {
        List<Integer> randomNumbers = getRandomNumbers(mineCount);
        for (int i = 0; i < randomNumbers.size(); i++) {
            int num = randomNumbers.get(i);
            map[num / COL][num % COL] = MINE; // 지뢰 추가
        }
    }

    /* 랜덤 숫자 생성 */
    private List<Integer> getRandomNumbers(int count) {
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < ROW * COL; i++) {
            numList.add(i); // index 저장
        }
        Collections.shuffle(numList); // 랜덤하게 위치 변경
        return numList.subList(0, count);
    }

    /* 지뢰가 아닌 칸에 지뢰의 갯수 표시 */
    private void setCntOfMine() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (map[i][j] == NONE) cntOfMineAroundTheSpace(i, j);
            }
        }
    }

    /* 자신을 제외한 주변 8칸에서 지뢰의 갯수 구하기 */
    private void cntOfMineAroundTheSpace(int row, int col) {
        // {row, col}
        int[][] around = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        for (int i = 0; i < around.length; i++) {
            if (isExistMine(row + around[i][0], col + around[i][1])) {
                map[row][col]++; // 해당 위치에 표시할 지뢰의 갯수 증가
            }
        }
    }

    /* 지뢰 존재 여부 확인 */
    private boolean isExistMine(int row, int col) {
        if (!checkValidation(row, col)) return false;
        return map[row][col] == MINE;
    }

    /* 배열 범위 유효성 체크 (new throw ArrayIndexOutOfBoundsException) */
    private boolean checkValidation(int row, int col) {
        return !(row < 0 || row >= ROW || col < 0 || col >= COL);
    }

    /* Map 출력 */
    private void printMap() {
        for (int i = 0; i < ROW; i++)
            System.out.println(Arrays.toString(map[i]));
    }
}