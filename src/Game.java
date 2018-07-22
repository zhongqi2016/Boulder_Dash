public class Game {
    public char[][] map = new char[50][50];
    public point robot;
    public int lamda = 0;
    int m;
    int n;
    //    R — робот
//    * — камень
//    L — закрытый выход
//    . — земля
//    # — стена
//    \ — λ
//    O — открытый выход
//    " " — пробел, пустая клетка

    public class point {
        int x, y;

        public void point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public void fall() {
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (map[x][y] == '*') {
                    switch (map[x][y + 1]) {
                        //Под камнем пустота — камень падает на 1 клетку вниз.
                        case ' ':
                            map[x][y] = ' ';
                            map[x][y + 1] = '*';
                            if (map[x][y + 2] == 'R') {
                            } //dead
                            break;
                        //Под камнем — камень, справа пусто и справа внизу пусто — камень падает по диагонали вправо.
                        case '*':
                            if (map[x + 1][y + 1] == ' ' && map[x + 1][y] == ' ') {
                                map[x][y] = ' ';
                                map[x + 1][y + 1] = '*';
                                if (map[x + 1][y + 2] == 'R') {
                                } //dead
                            }
                            // //Под камнем — камень, слева пусто и слева внизу пусто — камень падает по диагонали влево.
                            else if (map[x - 1][y + 1] == ' ' && map[x - 1][y] == ' ') {
                                map[x][y] = ' ';
                                map[x - 1][y + 1] = '*';
                                if (map[x - 1][y + 2] == 'R') {
                                } //dead
                            }
                            break;
                        //Под камнем — λ, справа пусто и справа внизу пусто — камень падает по диагонали вправо.
                        case '\\':
                            if (map[x + 1][y] == ' ' && map[x + 1][y + 1] == ' ') {
                                map[x][y] = ' ';
                                map[x + 1][y + 1] = '*';
                                if (map[x + 1][y + 2] == 'R') {
                                } //dead
                            }
                            break;
                    }
                }
            }
        }

    }


    void RightOrLeft(boolean flag){
        int n;
        if (flag) n=1;
        else n=-1;
        switch (map[robot.x + n][robot.y]) {
            case ' ':
                map[robot.x][robot.y] = ' ';
                robot.x+=n;
                map[robot.x][robot.y] = 'R';
                break;
            case '.':
                map[robot.x][robot.y] = ' ';
                robot.x+=n;
                map[robot.x][robot.y] = 'R';
                break;
            case '*':
                if (map[robot.x + 2*n][robot.y] == ' ') {
                    map[robot.x][robot.y] = ' ';
                    robot.x+=n;
                    map[robot.x][robot.y] = 'R';
                    map[robot.x + n][robot.y] = '*';
                }
                break;
            case '\\':
                lamda++;
                map[robot.x][robot.y] = ' ';
                robot.x+=n;
                map[robot.x][robot.y] = 'R';
                break;
            case '0':
                //win
                break;
        }
    }
    void UpOrDown(boolean flag){
        int n;
        if (flag) n=1;
        else n=-1;
        switch (map[robot.x][robot.y+n]){
            case ' ':
                map[robot.x][robot.y] = ' ';
                robot.y+=n;
                map[robot.x][robot.y] = 'R';
                break;
            case '\\':
                lamda++;
                map[robot.x][robot.y] = ' ';
                robot.y+=n;
                map[robot.x][robot.y] = 'R';
                break;
            case '.':
                map[robot.x][robot.y] = ' ';
                robot.y+=n;
                map[robot.x][robot.y] = 'R';
                break;
            case '0':
                //win
                break;
        }
    }
    public void turnRight() {
        RightOrLeft(true);
        fall();
    }
    public void turnLeft(){
        RightOrLeft(false);
        fall();
    }
    public void turnUp(){
        UpOrDown(false);
        fall();
    }
    public void turnDown(){
        UpOrDown(true);
        fall();
    }
}