package sutda;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/** 로그인 계정과 게임 기록을 컴퓨터의 data 폴더에 저장합니다. */
public class SaveData {
    private static final File DATA_DIR = new File("data");
    private static final File ACCOUNT_FILE = new File(DATA_DIR, "accounts.txt");
    private static final File RECORD_FILE = new File(DATA_DIR, "game_records.txt");

    /**
     * 로그인 정보를 확인합니다.
     * 0 = 비밀번호 오류, 1 = 기존 계정 로그인, 2 = 처음 로그인한 아이디라 새 계정 생성
     */
    static int login(String id, String password) {
        try {
            DATA_DIR.mkdirs();
            String passwordHash = hash(password);

            if (ACCOUNT_FILE.exists()) {
                List<String> lines = Files.readAllLines(ACCOUNT_FILE.toPath(), StandardCharsets.UTF_8);
                for (String line : lines) {
                    String[] value = line.split("\\t", 2);
                    if (value.length == 2 && value[0].equals(id)) {
                        return value[1].equals(passwordHash) ? 1 : 0;
                    }
                }
            }

            String account = id + "\t" + passwordHash + System.lineSeparator();
            Files.write(ACCOUNT_FILE.toPath(), account.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return 2;
        } catch (Exception e) {
            return -1;
        }
    }

    /** 한 판이 끝날 때 사용자 결과와 AI가 포함된 판 결과를 파일에 한 줄씩 저장합니다. */
    static void saveRecord(String id, String summary, String message, long money) {
        try {
            DATA_DIR.mkdirs();

            String result = "LOSE";
            if (message.contains("재경기"))
                result = "RETRY";
            else if (message.contains("무승부"))
                result = "DRAW";
            else if (message.contains("동점:") && message.contains(id))
                result = "DRAW";
            else if (message.contains("승자: " + id) || message.contains("최후의 승자: " + id))
                result = "WIN";

            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String line = time + "\t" + id + "\t" + result + "\t" + money + "\t"
                    + clean(summary) + "\t" + clean(message) + System.lineSeparator();

            Files.write(RECORD_FILE.toPath(), line.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (Exception ignored) {
        }
    }

    /** 저장된 기록에서 현재 아이디의 승/패/무승부 횟수를 계산합니다. */
    static String getStats(String id) {
        int win = 0;
        int lose = 0;
        int draw = 0;

        try {
            if (RECORD_FILE.exists()) {
                for (String line : Files.readAllLines(RECORD_FILE.toPath(), StandardCharsets.UTF_8)) {
                    String[] value = line.split("\\t", 6);
                    if (value.length < 3 || !value[1].equals(id))
                        continue;
                    if (value[2].equals("WIN"))
                        win++;
                    else if (value[2].equals("LOSE"))
                        lose++;
                    else if (value[2].equals("DRAW"))
                        draw++;
                }
            }
        } catch (Exception ignored) {
        }

        return win + "승 " + lose + "패 " + draw + "무";
    }

    /** 비밀번호 원문을 저장하지 않도록 SHA-256 문자열로 바꿉니다. */
    private static String hash(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder result = new StringBuilder();
        for (byte b : bytes)
            result.append(String.format("%02x", b));
        return result.toString();
    }

    private static String clean(String text) {
        return text.replace('\t', ' ').replace('\n', ' ').replace('\r', ' ');
    }
}
