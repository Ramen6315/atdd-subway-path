package wooteco.subway.admin.acceptance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import wooteco.subway.admin.dto.StationResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({"/schema-test.sql","/truncate.sql"})
public class StationAcceptanceTest extends AcceptanceTest {
    @DisplayName("지하철역을 관리한다")
    @Test
    void manageStation() {
        // when
        createStation(강남역);
        createStation(역삼역);
        createStation(선릉역);
        // then
        List<StationResponse> stations = getStations();
        assertThat(stations.size()).isEqualTo(3);

        // when
        deleteStation(stations.get(0).getId());
        // then
        List<StationResponse> stationsAfterDelete = getStations();
        assertThat(stationsAfterDelete.size()).isEqualTo(2);
    }

    List<StationResponse> getStations() {
        return
                given().when().
                        get("/stations").
                        then().
                        log().all().
                        extract().
                        jsonPath().getList(".", StationResponse.class);
    }

    void deleteStation(Long id) {
        given().when().
                delete("/stations/" + id).
                then().
                log().all();
    }
}
