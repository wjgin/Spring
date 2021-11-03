package jcpdev.day2.service;

import jcpdev.day2.dto.BoardDto;
import jcpdev.day2.mapper.FreeboardMapper;

public class FreeboardServiceImpl implements FreeboardService {
	
	private final FreeboardMapper freeDao;
	
	public FreeboardServiceImpl(FreeboardMapper freeDao) {
		this.freeDao = freeDao;
	}
	
	@Override
	public int getCount() {
		return freeDao.getCount();
	}

	@Override
	public BoardDto getBoardOne(int idx) {
		return freeDao.getBoardOne(idx);
	}

}
