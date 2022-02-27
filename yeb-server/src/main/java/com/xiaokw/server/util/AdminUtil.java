package com.xiaokw.server.util;

import com.xiaokw.server.entity.TAdmin;
import org.springframework.security.core.context.SecurityContextHolder;

public class AdminUtil {
    public static TAdmin getCurrentAdmin(){
        return ((TAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
