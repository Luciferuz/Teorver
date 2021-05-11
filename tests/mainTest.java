import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class mainTest {
    @org.junit.jupiter.api.Test

    public void task_2_29() {
        double n = 11;
        double m = 3;
        double k = 3;
        double tests = 10000000;
        double prob = 0;
        double analytically = (n - k * m) / (n + m);
        ArrayList<Integer> votes = new ArrayList<>();

        //заполнил массив голосами за первого и второго и перемешал
        for (int i = 0; i < n; i++) votes.add(1);
        for (int i = 0; i < m; i++) votes.add(2);
        Collections.shuffle(votes);

        for (int x = 0; x < tests; x++) {
            Collections.shuffle(votes);
            int votesFirst = 0;
            int votesSecond = 0;
            int check = 0;

            for (Integer element : votes) {
                if (element == 1) votesFirst++;
                if (element == 2) votesSecond++;
                if (votesFirst > k * votesSecond) check++;
            }
            if (check == m + n) prob++;
        }
        System.out.println("Аналитическое = " + analytically);
        System.out.println("Моделирование = " + prob / tests);
    }

    @org.junit.jupiter.api.Test
    public void task_4_17() { //не работает
        double tests = 10000000;
        double prob = 0;
        double prob2 = 0;
        double p1 = 0.2; //бракованная в результате мех
        double p2 = 0.1; //бракованная в результате терм
        double p3 = 0.4; //брак неустранимый мех
        double p4 = 0.3; //брак неустранимый терм
        double find = -1; //найти
        double analytically1 = -1.0 / (Math.log(p1 * p3));
        double analytically2 = 1 - (1.0 - p1 * p3) * (1.0 - p1 * p3) * (1.0 - p1 * p3) * (1.0 - p2 * p4) * (1.0 - p2 * p4) * (1.0 - p2 * p4);

        //for (int x = 0; x < tests; x++) {
//            Random random = new Random();
//            int x1 = random.nextInt(10) + 1; //брак мех
//            int x2 = random.nextInt(10) + 1; //брак терм
//            int x3 = random.nextInt(10) + 1; //неустранимиый мех
//            int x4 = random.nextInt(10) + 1; //неустранимый терм
//
//            prob2 = prob2 + (-1.0) / (Math.log(x1 * x3));
//            if (x3 < 4 && x4 < 3) prob2++;


        while (true) {
            for (int i = 0; i < tests; i++) {
                if (Math.random() < p1) {
                    if (Math.random() < find) prob++;
                }
            }
            if (prob / tests < 0.9) break;
            prob = 0;
            find = find + 0.001;
            System.out.println(prob / tests);
        }


        //}

        System.out.println("Аналитическое а = " + analytically1);
        System.out.println("Моделирование а = " + find);
        // System.out.println("Аналитическое б = " + analytically2);
        //System.out.println("Моделирование б = " + prob2 / tests);
    }

    @org.junit.jupiter.api.Test
    public void task_5_4() {
        double tests = 100000000;
        double prob = 0;
        double r1 = 17.0; //R круга
        double r2 = 5.0; //r монеты
        double analytically = 2.0 * r2 * r2 / (r1 * r1);

        double s1 = r1 * r1; //площадь круга (без пи потому что сократится)
        double s2 = 2 * r2 * r2; //суммарная площадь монет (без пи потому что сократится)
        double p = s2 / s1; //отношение площади монет к площади круга (какая вероятность, что в монету попадет)
        for (int x = 0; x < tests; x++) {
            double randomNumber = (Math.random());
            if (randomNumber < p) prob++; //если точка попала на одну из монет
        }
        System.out.println("Аналитическое = " + analytically);
        System.out.println("Моделирование = " + prob / tests);
    }

    @org.junit.jupiter.api.Test
    public void task_6_1() {
        double tests = 10000000;
        double prob = 0;
        double analytically = 13.0 / 132.0;
        for (int x = 0; x < tests; x++) {
            //единица - нормальная деталь, ноль - бракованная
            ArrayList<Integer> first = new ArrayList<>();
            ArrayList<Integer> second = new ArrayList<>();
            for (int i = 0; i < 11; i++) first.add(1);
            for (int i = 0; i < 9; i++) second.add(1);
            first.add(0);
            second.add(0);
            Collections.shuffle(first);
            Collections.shuffle(second);

            //взяли случайное из первой партии
            int random1 = (int) (Math.random() * 12);
            int take1 = first.get(random1);
            //положили во вторую партию и перемешали
            second.add(take1);
            Collections.shuffle(second);
            //взяли случайное из второй партии
            int random2 = (int) (Math.random() * 11);
            int take2 = second.get(random2);
            if (take2 == 0) prob++;
        }
        System.out.println("Аналитическое = " + analytically);
        System.out.println("Моделирование = " + prob / tests);
    }

    @org.junit.jupiter.api.Test
    public void task_7_4() {
        double tests = 10000000;
        int analytically = 5;
        int answer = 0;
        int[] answers = new int[5]; //массив для вариантов с разным числом бракованных изделий

        for (int x = 0; x < tests; x++) {
            for (int numOfBad = 1; numOfBad <= 5; numOfBad++) {
                double p = numOfBad / 5.0; //вероятность что плохое
                for (int t = 0; t < 5; t++) { //проходимся по каждому изделию (всего 5)
                    if (Math.random() < p) answers[numOfBad - 1]++;
                }
            }
        }

        //создали еще один массив и отсортировали его по убыванию, чтобы найти самый большой элемент
        int[] tempArr = answers;
        tempArr = Arrays.stream(tempArr).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        System.out.println("unsorted " + Arrays.toString(answers)); //вывод массива на экран (неотсортированного)
        System.out.println("sorted   " + Arrays.toString(tempArr)); //вывод массива на экран (отсортированного)
        System.out.println("бракованных изделий 1 = " + answers[0] / tests);
        System.out.println("бракованных изделий 2 = " + answers[1] / tests);
        System.out.println("бракованных изделий 3 = " + answers[2] / tests);
        System.out.println("бракованных изделий 4 = " + answers[3] / tests);
        System.out.println("бракованных изделий 5 = " + answers[4] / tests);
        int max = tempArr[0]; //максимальный элемент
        for (int i = 1; i <= 5; i++) { //ищем индекс, соответствующий этому элементу в неотсортированном массиве
            if (answers[i - 1] == max) answer = i;
        }

        System.out.println("Аналитическое = " + analytically);
        System.out.println("Моделирование = " + answer);
        //больше всего совпало предполоение, что бракованных изделий - 5
    }

    @org.junit.jupiter.api.Test
    public void task_8_2() {
        double tests = 100000000;
        double prob1 = 0;
        double prob2 = 0;
        double analytically1 = 252.0 / (Math.pow(2.0, 10.0));
        double analytically2 = 1.0 - (67.0 / 1024.0);

        for (int x = 0; x < tests; x++) {
            int totalMale = 0;
            for (int i = 0; i < 10; i++) {
                if (Math.random() < 0.5) totalMale++; //считаем сколько мальчиков родилось
            }

            if (totalMale == 5) prob1++;
            if (totalMale >= 3 && totalMale <= 8) prob2++;
        }

        System.out.println("Аналитическое а = " + analytically1);
        System.out.println("Моделирование а = " + prob1 / tests);
        System.out.println("Аналитическое б = " + analytically2);
        System.out.println("Моделирование б = " + prob2 / tests);
    }

    @org.junit.jupiter.api.Test
    public void task_9_5() {
        double tests = 50000000;
        double prob1 = 0;
        double prob2 = 0;
        List<Integer> goodSeating = List.of(0, 1, 2, 2, 3, 4);
        // аналитические значения вычислил с помощью WolframAlpha
        double analytically1 = 0.00343828589;
        double analytically2 = 0.13753143575;

        for (int x = 0; x < tests; x++) {
            int carriage1 = 0;
            int carriage2 = 0;
            int carriage3 = 0;
            int carriage4 = 0;
            int carriage5 = 0;
            int carriage6 = 0;
            // рассадка людей по вагонам
            for (int man = 0; man < 12; man++) {
                int randomNumber = 1 + (int) (Math.random() * 6);
                switch (randomNumber) {
                    case 1:
                        carriage1++;
                        break;
                    case 2:
                        carriage2++;
                        break;
                    case 3:
                        carriage3++;
                        break;
                    case 4:
                        carriage4++;
                        break;
                    case 5:
                        carriage5++;
                        break;
                    case 6:
                        carriage6++;
                        break;
                }
            }

            // если в каждый вагон по 2 человека село
            if (carriage1 == 2 && carriage2 == 2 && carriage3 == 2 &&
                    carriage4 == 2 && carriage5 == 2 && carriage6 == 2) prob1++;

            // добавляю все в лист и сортирую его, чтобы сравнить с благоприятной
            // рассадкой людей (внес это в goodSeating)
            ArrayList<Integer> array = new ArrayList<>();
            array.add(carriage1);
            array.add(carriage2);
            array.add(carriage3);
            array.add(carriage4);
            array.add(carriage5);
            array.add(carriage6);
            Collections.sort(array);
            if (array.equals(goodSeating)) prob2++;
        }

        System.out.println("Аналитическое а = " + analytically1);
        System.out.println("Моделирование а = " + prob1 / tests);
        System.out.println("Аналитическое б = " + analytically2);
        System.out.println("Моделирование б = " + prob2 / tests);
    }

    @org.junit.jupiter.api.Test
    public void task_katya_2_12() {
        double tests = 10000000;
        double prob = 0;
        double analytically = 5.0 / 14.0;
        List<Integer> numbers = List.of(2, 4, 6, 7, 8, 11, 12, 13);

        for (int i = 0; i < tests; i++) {
            ArrayList<Integer> array = new ArrayList<>(numbers);
            Collections.shuffle(array);

            int randomNumber1 = (int) (Math.random() * 8);
            int firstNum = array.get(randomNumber1);
            array.remove(randomNumber1);

            int randomNumber2 = (int) (Math.random() * 7);
            int secondNum = array.get(randomNumber2);

            if (firstNum % 2 == 0 && secondNum % 2 == 0) prob++;
        }

        System.out.println("Аналитическое = " + analytically);
        System.out.println("Моделирование = " + prob / tests);
    }

    @org.junit.jupiter.api.Test
    public void task_katya_5_26() {
        double tests = 10000000;
        double prob = 0;
        int n = 12; //white
        int m = 17; //black
        double analytically = (m + n) / (2.0 * m + n);

        ArrayList<Integer> balls = new ArrayList<>();
        for (int i = 0; i < n; i++) balls.add(1); //1 - белый
        for (int i = 0; i < m; i++) balls.add(0); //0 - черный
        Collections.shuffle(balls);

        for (int i = 0; i < tests; i++) {
            Collections.shuffle(balls);

            while (true) {
                //первый игрок берет
                int random = (int) (Math.random() * (m + n));
                if (balls.get(random) == 1) { //если он взял белый, то благоприятный исход - выходим из цикла и запускаем следующий тест
                    prob++;
                    break;
                } else { //теперь берет второй если первый не достал белый
                    random = (int) (Math.random() * (m + n));
                    if (balls.get(random) == 1)
                        break; //если он вытянул белый, то неудачный исход, а если не вытянул белый (вытянул черный - продолжаем брать шары)
                }
            }
        }
        System.out.println("Аналитическое = " + analytically);
        System.out.println("Моделирование = " + prob / tests);
    }

    @org.junit.jupiter.api.Test
    public void task_katya_6_14() {
        double tests = 10000000;
        double prob = 0;
        double analytically = 528.0 / 5915.0;
        ArrayList<Integer> balls = new ArrayList<>();

        for (int z = 0; z < tests; z++) {
            for (int i = 0; i < 9; i++) balls.add(1); //1 - новый
            for (int i = 0; i < 6; i++) balls.add(0); //0 - не новый
            Collections.shuffle(balls);

            //берем для первой игры случайные три мяча
            int random = (int) (Math.random() * 15);
            balls.remove(random);
            random = (int) (Math.random() * 14);
            balls.remove(random);
            random = (int) (Math.random() * 13);
            balls.remove(random);

            //положили 3 мяча обратно (они уже не новые) и перемешали массив
            for (int i = 0; i < 3; i++) balls.add(0); //0 - не новый
            Collections.shuffle(balls);

            //берем случайные три мяча для второй игры
            random = (int) (Math.random() * 15);
            int first = balls.get(random);
            balls.remove(random);

            random = (int) (Math.random() * 14);
            int second = balls.get(random);
            balls.remove(random);

            random = (int) (Math.random() * 13);
            int third = balls.get(random);

            if (first == 1 && second == 1 && third == 1) prob++;
            balls.clear();
        }

        System.out.println("Аналитическое = " + analytically);
        System.out.println("Моделирование = " + prob / tests);
    }

    @org.junit.jupiter.api.Test
    public void task_katya_4_15() {
        double tests = 10000000;
        double prob = 0;
        double analytically = 0.75;
        //предположили что вероятность попадания во второй 1.0 (но такого не может быть)
        double find = 1.0; //100%

        while (true) {
            for (int i = 0; i < tests; i++) {
                if (Math.random() < 2.0 / 3.0) { //пробили первую мишень
                    if (Math.random() < find) prob++; //пробили и вторую мишень
                }
            }
            if (prob / tests < 0.5) break; //пробили сразу две за два выстрела вероятность = 0.5
            // если у нас получилась адекватная вероятность попадания в обе мишени за два выстрела - выходим из цикла
            // если нет (то есть она не 0.5 как по условию) - повторяем все но
            // с чуть меньшей предполагаемой вероятностью попадания во второй
            prob = 0;
            find = find - 0.001;
            System.out.println(prob / tests);
        }

        System.out.println("Аналитическое = " + analytically);
        System.out.println("Моделирование = " + find);
    }

    @org.junit.jupiter.api.Test
    public void task_danya_2_1() {
        double tests = 50000000;
        double n = 123400.0;
        double r = 100.0;
        double m = 500.0;
        double analytically = m * r / n;
        int prob = 0;
        double total = n / r;
        double p = m / total;

        for (int i = 0; i < tests; i++) {
            if (Math.random() < p) prob++;
        }

        System.out.println("Аналитическое = " + analytically);
        System.out.println("Моделирование = " + prob / tests);
    }

    @org.junit.jupiter.api.Test
    public void task_danya_3_23() {
        double tests = 50000000;
        double anal = 0.05;
        int prob = 0;
        System.out.println("Аналитическое = " + anal);
        System.out.println("Моделирование = " + prob / tests);
    }

    @org.junit.jupiter.api.Test
    public void task_danya_4_14() {
        double tests = 10000000;
        double analytically = 0.7 * 0.7 * (1 - 0.6 * 0.6);
        int prob = 0;

        for (int i = 0; i < tests; i++) {
            double random1 = (Math.random()); //вероятность выигрыша для второго
            double random2 = (Math.random()); //вероятность выигрыша для третьего
            if (random1 < 0.3 || random2 < 0.3) { //если первый не проиграл, то ходит на второго и третьего
                double random3 = (Math.random()); //выиграл ли у второго
                double random4 = (Math.random()); //выиграл ли у третьего
                if (random3 < 0.4 || random4 < 0.4) {
                    prob++;
                }
            }

        }
        System.out.println("Аналитическое = " + analytically);
        System.out.println("Моделирование = " + prob / tests);
    }

    @org.junit.jupiter.api.Test
    public void task_danya_8_31() {
        double tests = 1000000;
        int analytically = 5;
        int prob = 0;
        int find = 0; //принимаем, что изначально число каналов = 0, потом будем постепенно увеличивать это количество
        int counter; //счетчик, какому количеству абонентов потребовалась линия связи

        while (true) { //пока не достигнем вероятности 0.99, ищем минимальное число каналов
            for (int i = 0; i < tests; i++) {
                counter = 0;
                for (int z = 1; z <= 10; z++) { //проходимся по 10 абонентам и считаем, скольким нужна линия
                    if (Math.random() < 0.2) counter++;
                }
                //если это количество абонентов меньше или равно числу каналов связи, то благоприятный исход
                if (counter <= find) prob++;
            }
            if (prob / tests >= 0.99) break; //если достигли нужной вероятности, выводим ответ
            //иначе - обнуляем prob и увеличиваем число каналов на единицу
            prob = 0;
            find++;
        }

        System.out.println("Аналитическое = " + analytically);
        System.out.println("Моделирование = " + find);
    }


}