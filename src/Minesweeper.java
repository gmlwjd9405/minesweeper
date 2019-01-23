import java.util.Arrays;
import java.util.Random;

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
        minesweeper.setCntOfMine(); // 각 사각형에 표시될 숫자를 "입력"하는 로직 부분
        minesweeper.printMap();
    }

    /* default 생성자 */
    public Minesweeper() {
        map = new char[ROW][COL];
    }

    /* map 초기화 */
    private void initMap() {
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++)
                map[i][j] = NONE;
    }

    /* 지뢰 위치 랜덤 생성 */
    private void createMine(int mineCnt) {
        Random random = new Random();

        while (mineCnt-- > 0) {
            int row = random.nextInt(ROW);
            int col = random.nextInt(COL);

            if (map[row][col] == MINE) { // 위치 다시 지정
                ++mineCnt;
                continue;
            }
            map[row][col] = MINE; // 지뢰 추가
        }
    }

    /* 지뢰가 아닌 칸에 지뢰의 갯수 표시 */
    private void setCntOfMine() {
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++)
                if (map[i][j] == NONE)
                    cntOfMineAroundTheSpace(i, j);
    }

    /* 자신을 제외한 주변 8칸에서 지뢰의 갯수 구하기 */
    private void cntOfMineAroundTheSpace(int row, int col) {
        int[] aroundRow = {row - 1, row - 1, row - 1, row, row, row + 1, row + 1, row + 1};
        int[] aroundCol = {col - 1, col, col + 1, col - 1, col + 1, col - 1, col, col + 1};

        for (int i = 0; i < aroundRow.length; i++)
            if (isExistMine(aroundRow[i], aroundCol[i]))
                map[row][col]++; // 해당 위치에 표시할 지뢰의 갯수 증가
    }

    /* 지뢰 존재 여부 확인 */
    private boolean isExistMine(int row, int col) {
        if (!checkValidation(row, col))
            return false;
        return map[row][col] == MINE;
    }

    /* 배열 범위 유효성 체크 (new throw ArrayIndexOutOfBoundsException) */
    private boolean checkValidation(int row, int col) {
        if (row < 0 || row >= ROW || col < 0 || col >= COL)
            return false;
        return true;
    }

    /* Map 출력 */
    private void printMap() {
        for (int i = 0; i < ROW; i++)
            System.out.println(Arrays.toString(map[i]));
    }
}