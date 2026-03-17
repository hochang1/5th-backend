package board.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

@Service
public class PagingService {
	
	// 화면당 보여줄 페이지의 개수
	private final static int PAGE_LENGTH = 10;
	
	// 화면에서 start, end 페이지 정의
	public List<Integer> getPagingNumbers(int pageNumber, int totalPages) {
		//현재 : 0~9, start : 0, end : start + page_length
		//마지막 : ?~?, start : ?, end : ? + page_length
		
		// 현재 페이지 블록
		int currentBlock = (pageNumber-1) / PAGE_LENGTH;
		int startPage = currentBlock * PAGE_LENGTH + 1;
		int endPage = Math.min(startPage + PAGE_LENGTH, totalPages + 1) ;
		
		return IntStream.range(startPage, endPage).boxed().toList();
	}
	
	public int getPageLength() {
		return PAGE_LENGTH;
	}
	
}