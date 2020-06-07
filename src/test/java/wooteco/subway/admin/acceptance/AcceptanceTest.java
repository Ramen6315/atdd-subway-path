package wooteco.subway.admin.acceptance;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import wooteco.subway.admin.dto.LineResponse;
import wooteco.subway.admin.dto.StationResponse;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/truncate.sql")
public class AcceptanceTest {
    static final String 강남역 = "강남역";
    static final String 역삼역 = "역삼역";
    static final String 선릉역 = "선릉역";
    static final String 삼성역 = "삼성역";
    static final String 종합운동장역 = "종합운동장역";
    static final String 잠실새내역 = "잠실새내역";
    static final String 잠실역 = "잠실역";
    static final String 양재역 = "양재역";
    static final String 매봉역 = "매봉역";
    static final String 도곡역 = "도곡역";
    static final String 대치역 = "대치역";
    static final String 학여울역 = "학여울역";
    static final String 대청역 = "대청역";
    static final String 수서역 = "수서역";
    static final String 가락시장역 = "가락시장역";
    static final String 송파역 = "송파역";
    static final String 석촌역 = "석촌역";
    static final String 양재시민의숲역 = "양재시민의숲역";
    static final String 청계산입구역 = "청계산입구역";
    static final String 판교역 = "판교역";
    static final String 정자역 = "정자역";
    static final String 한티역 = "한티역";
    static final String 구룡역 = "구룡역";
    static final String 개포동역 = "개포동역";
    static final String 대모산입구역 = "대모산입구역";

    static final String LINE_NAME_2 = "2호선";
    static final String LINE_NAME_3 = "3호선";
    static final String 분당선 = "분당선";
    static final String 신분당선 = "신분당선";

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    public static RequestSpecification given() {
        return RestAssured.given().log().all();
    }

    LineResponse createLine(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("startTime", LocalTime.of(5, 30).format(DateTimeFormatter.ISO_LOCAL_TIME));
        params.put("endTime", LocalTime.of(23, 30).format(DateTimeFormatter.ISO_LOCAL_TIME));
        params.put("intervalTime", "10");

        return
                given().
                        body(params).
                        contentType(MediaType.APPLICATION_JSON_VALUE).
                        accept(MediaType.APPLICATION_JSON_VALUE).
                        when().
                        post("/lines").
                        then().
                        log().all().
                        statusCode(HttpStatus.CREATED.value()).
                        extract().as(LineResponse.class);
    }

    StationResponse createStation(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);

        return
                given().
                        body(params).
                        contentType(MediaType.APPLICATION_JSON_VALUE).
                        accept(MediaType.APPLICATION_JSON_VALUE).
                        when().
                        post("/stations").
                        then().
                        log().all().
                        statusCode(HttpStatus.CREATED.value()).
                        extract().as(StationResponse.class);
    }

    void addLineStation(Long lineId, Long preStationId, Long stationId) {
        addLineStation(lineId, preStationId, stationId, 10, 10);
    }

    void addLineStation(Long lineId, Long preStationId, Long stationId, Integer distance, Integer duration) {
        Map<String, String> params = new HashMap<>();
        params.put("preStationId", preStationId == null ? "" : preStationId.toString());
        params.put("stationId", stationId.toString());
        params.put("distance", distance.toString());
        params.put("duration", duration.toString());

        given().
                body(params).
                contentType(MediaType.APPLICATION_JSON_VALUE).
                accept(MediaType.APPLICATION_JSON_VALUE).
                when().
                post("/lines/" + lineId + "/stations").
                then().
                log().all().
                statusCode(HttpStatus.OK.value());
    }

}
