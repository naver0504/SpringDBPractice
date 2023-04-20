package hello.exception.api;

import hello.exception.exception.BadRequestException;
import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
public class ApiExceptionController {

    MessageSource messageSource;

//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(TypeMismatchException.class)
//    public ErrorResult typeMismatchException(TypeMismatchException e) {
//        return new ErrorResult("TYPEMISMATCH", e.getMessage());
//    }

    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) throws UserException {

        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }

        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }
        if (id.equals(("user-ex"))) {
            throw new UserException("사용자 오류");
        }
        return MemberDto.builder()
                .memberId(id)
                .name("hello " + id).build();

    }

    @GetMapping("/api/response-status-ex1")
    public String responseStatusEx1() {
        log.info("response-status-ex1");
        throw new BadRequestException();
    }

    @GetMapping("/api/response-status-ex2")
    public String responseStatusEx2() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalArgumentException());
    }

    @GetMapping("/api/default-handler-ex")
    public String defaultException(@RequestParam Integer data) {
        return "ok";
    }



    @Builder
    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;


    }
}
