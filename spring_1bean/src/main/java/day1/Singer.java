package day1;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Singer { // vo, dto 객체들은 bean으로 관리하지 않음. -> Model객체
	private int idx;
	private String singer;
	private List<String> albums;
}
