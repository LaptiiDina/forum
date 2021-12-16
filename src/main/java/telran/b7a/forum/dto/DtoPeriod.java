package telran.b7a.forum.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DtoPeriod {
	@JsonFormat(pattern = "[yyyy-MM-dd][dd-MM-yyyy]")
	LocalDate dateFrom;
	@JsonFormat(pattern = "[yyyy-MM-dd][dd-MM-yyyy]")
	LocalDate dateTo;
	
}
