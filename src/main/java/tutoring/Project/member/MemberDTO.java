package tutoring.Project.member;

import tutoring.Project.base.BaseDto;

public class MemberDTO extends BaseDto<MemberEntity> {
    protected String[] fillable = {"id", "password", "name"};

    private String id;
    private String password;
    private String name;

    public MemberDTO(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }


}
