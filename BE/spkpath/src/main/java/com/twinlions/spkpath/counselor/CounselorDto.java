package com.twinlions.spkpath.counselor;

import com.twinlions.spkpath.user.entity.User;
import lombok.*;

@Data // Getter Setter 정의 없이 사용할 수 있음
@NoArgsConstructor
@AllArgsConstructor
public class CounselorDto {
    private String userId;
    private User user;
    private String cnslrTeam;
    private String cnslrExp;
    private String cnslrTag;
    private String cnslrBoundary;
}
