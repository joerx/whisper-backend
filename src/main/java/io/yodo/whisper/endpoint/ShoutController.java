package io.yodo.whisper.endpoint;

import io.yodo.whisper.entity.PageResponse;
import io.yodo.whisper.entity.Shout;
import io.yodo.whisper.service.ShoutService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoutController {

    private final int pageSize;

    private final ShoutService shoutService;

    public ShoutController(
            @Value("${paging.pageSize}") int pageSize,
            ShoutService shoutService
    ) {
        this.pageSize = pageSize;
        this.shoutService = shoutService;
    }

    @GetMapping("/shouts")
    public PageResponse<Shout> getShouts(@RequestParam(defaultValue = "1") int p) {
        PageRequest pr = PageRequest.of(p - 1, pageSize);
        Page<Shout> shouts = shoutService.findAll(pr);
        return PageResponse.from(shouts);
    }

    @GetMapping("/shouts/{id}")
    public Shout getShout(@PathVariable int id) {
        return shoutService.findById(id);
    }

    @PostMapping("/shouts")
    public Shout createShout(@RequestBody Shout shout) {
        return shoutService.create(shout);
    }

    @PutMapping("/shouts/{id}")
    public Shout updateShout(@RequestBody Shout shout, @PathVariable int id) {
        return shoutService.update(id, shout);
    }

    @DeleteMapping("/shouts/{id}")
    public Shout deleteShout(@PathVariable int id) {
        return shoutService.deleteById(id);
    }
}
