package com.example.movie.api;

import com.example.movie.entity.MessageBoards;
import com.example.movie.repository.MessageBoardsRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class MessageBoardsApiController {
    private final MessageBoardsRepository messageBoardsRepository;

    public MessageBoardsApiController(MessageBoardsRepository messageBoardsRepository) {
        this.messageBoardsRepository = messageBoardsRepository;
    }

    // 新建
    @PostMapping("/api/messageBoards/create")
    public HashMap<String, Object> create(
            @RequestParam String content,
            HttpSession session
    ) {

        Integer userId = (Integer) session.getAttribute("userId");
        String s_id = session.getId();

        System.out.println("Session ID: " + s_id);
        System.out.println("User ID: " + userId);

        HashMap<String, Object> res = new HashMap<>();
        if (userId == null) {
            res.put("code", 401);
            res.put("message", "未登录");
            res.put("data", null);
            return res;
        }
        MessageBoards messageBoards = new MessageBoards();
        messageBoards.setUserId(userId);
        messageBoards.setContent(content);
        messageBoards.setCreateDateTime(LocalDateTime.now());
        this.messageBoardsRepository.save(messageBoards);
        res.put("code", 0);
        res.put("message", "success");
        res.put("data", null);
        return res;
    }

    // 列表(查询)
    @GetMapping("/api/messageBoards/index")
    public HashMap<String, Object> index() {
        List<MessageBoards> all = this.messageBoardsRepository.findAll();
        HashMap<String, Object> res = new HashMap<>();
        res.put("code", 0);
        res.put("message", "success");
        res.put("data", all);
        return res;
    }

    // 删除
    @DeleteMapping("/api/messageBoards/delete")
    public HashMap<String, Object> delete(@RequestParam int id) {
        HashMap<String, Object> res = new HashMap<>();
        // 查询
        Optional<MessageBoards> byId = this.messageBoardsRepository.findById(id);
        MessageBoards messageBoards = byId.orElse(null);
        if (messageBoards == null) {
            res.put("code", 404);
            res.put("message", "数据未找到");
            res.put("data", null);
            return res;
        }
        // 修改删除时间
        messageBoards.setDeleteDateTime(LocalDateTime.now());
        this.messageBoardsRepository.save(messageBoards);
        res.put("code", 0);
        res.put("message", "删除成功");
        res.put("data", null);
        return res;
    }
}
