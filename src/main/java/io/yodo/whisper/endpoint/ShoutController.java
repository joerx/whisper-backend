package io.yodo.whisper.endpoint;

import io.yodo.whisper.entity.Shout;
import io.yodo.whisper.service.ShoutService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoutController {

    private final ShoutService shoutService;

    public ShoutController(ShoutService shoutService) {
        this.shoutService = shoutService;
    }

    @GetMapping("/shouts")
    public List<Shout> getShouts() {
        return shoutService.findAll();
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
