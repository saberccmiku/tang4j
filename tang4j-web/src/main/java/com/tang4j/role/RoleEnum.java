package com.tang4j.role;

public enum RoleEnum implements RoleOperation {


    /**
     * 超级管理员
     */
    ROLE_ROOT_ADMIN {
        @Override
        public void op() {
            //TODO
            System.out.println("我是超级管理员");
        }
    },
    /**
     * 维护人员
     */
    ROLE_MAINTAIN {
        @Override
        public void op() {
            //TODO
            System.out.println("我是维护人员");
        }
    },
    /**
     * 一般用户
     */
    ROLE_NORMAL {
        @Override
        public void op() {
            //TODO
            System.out.println("我是一般用户");
        }
    }

}
