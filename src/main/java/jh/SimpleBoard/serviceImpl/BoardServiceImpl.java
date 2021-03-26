package jh.SimpleBoard.serviceImpl;

import jh.SimpleBoard.mapper.BoardMapper;
import jh.SimpleBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
}
