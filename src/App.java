import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class App {

    private Scanner sc;
    private List<WiseSaying> wiseSayings;
    private int wiseSayingLastId;

    public App() {
        sc = new Scanner(System.in);
        wiseSayings = new ArrayList<>();
        wiseSayingLastId = 0;
    }

    public void run() {
        System.out.println("== 명언 SSG ==");

        outer:
        while (true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim();

            Rq rq = new Rq(cmd);

            switch (rq.getPath()) {
                case "등록":
                    write(rq);
                    break;
                case "삭제":
                    delete(rq);
                    break;
                case "목록":
                    list(rq);
                    break;
                case "종료":
                    break outer;
            }
        }

        sc.close();
    }

    private void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------");
        for (int i = wiseSayings.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying_ = wiseSayings.get(i);
            System.out.printf("%d / %s / %s\n", wiseSaying_.id, wiseSaying_.content, wiseSaying_.author);
        }
    }

    private void delete(Rq rq) {
        int paramId = rq.getIntParam("id", 0);

        if (paramId == 0) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        WiseSaying foundWiseSaying = findById(paramId);
        wiseSayings.remove(foundWiseSaying);
        System.out.printf("%d번 명언이 삭제되었습니다.\n", paramId);
    }

    private WiseSaying findById(int paramId) {

        for (WiseSaying findSayings : wiseSayings) {
            if (findSayings.id == paramId) {
                return findSayings;
            }
        }
        System.out.printf("%d번 명언은 존재하지 않습니다..\n", paramId);
        return null;
    }

    private void write(Rq rq) {
        System.out.printf("명언 : ");
        String content = sc.nextLine().trim();
        System.out.printf("작가 : ");
        String author = sc.nextLine().trim();
        int id = ++wiseSayingLastId; // 명언 글 번호 증가

        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayings.add(wiseSaying);

        System.out.printf("%d번 명언이 등록되었습니다.\n", id);
    }
}