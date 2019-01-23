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
        List<Integer> randomNumbers = minesweeper.getRandomNumbers(THE_NUM_OF_MINE);
        minesweeper.createMine(randomNumbers);
        minesweeper.setCntOfMine(randomNumbers);
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

    /* 랜덤 숫자(지뢰 위치) 생성 */
    private List<Integer> getRandomNumbers(int count) {
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < ROW * COL; i++) {
            numList.add(i); // index 저장
        }
        Collections.shuffle(numList); // 랜덤하게 위치 변경
        return numList.subList(0, count);
    }

    /* 지뢰 생성 */
    private void createMine(List<Integer> mineLocation) {
        for (int i = 0; i < mineLocation.size(); i++) {
            int num = mineLocation.get(i);
            map[num / COL][num % COL] = MINE; // 지뢰 추가
        }
    }

    /* 지뢰가 아닌 칸에 지뢰의 갯수 표시 */
    private void setCntOfMine(List<Integer> mineLocation) {
        for (int i = 0; i < mineLocation.size(); i++) { // 지뢰가 있는 위치 주변만 확인
            int num = mineLocation.get(i);
            updateCntOfMine(num / COL, num % COL);
        }
    }

    /* 자신을 제외한 주변 8칸의 갯수 1씩 증가 */
    private void updateCntOfMine(int row, int col) {
        int[][] around = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}}; // {row, col}

        for (int i = 0; i < around.length; i++) {
            int checkRow = row + around[i][0];
            int checkCol = col + around[i][1];

            if (isNotExistMine(checkRow, checkCol)) { // 지뢰가 아니면
                map[checkRow][checkCol]++; // 해당 위치에 표시할 지뢰의 갯수 증가
            }
        }
    }

    /* 지뢰 존재 여부 확인 */
    private boolean isNotExistMine(int row, int col) {
        if (!checkValidation(row, col)) return false;
        return map[row][col] != MINE;
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