package com.spring.boardweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BoardMapper {

	@Select("SELECT IFNULL(MAX(BOARD_SEQ), 0) + 1 FROM T_BOARD")
	int getNextBoardSeq();

	@Update("UPDATE T_BOARD SET BOARD_SEQ = BOARD_SEQ - 1 WHREE BOARD_SEQ > #{board_seq}")
	void updateBoardSeq(int boardSeq);

}
