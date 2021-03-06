package ldt.springframework.tigibusiness.event.security;

import org.springframework.context.ApplicationEvent;

/*
 * author: Luu Duc Trung
 * https://github.com/luuductrung1234
 * ---
 * 7/31/18
 */
public class LoginSuccessEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public LoginSuccessEvent(Object source) {
        super(source);
    }
}
