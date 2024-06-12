<template>
    <div class="header-container">
        <div class="l-content">
            <!-- 面包屑 -->
            <span class="text" @click="handleMainPage">首页</span>
        </div>
        <div class="r-content">
            <el-dropdown>
                <span class="el-dropdown-link"><img src="../assets/user.jpg" class="user"></span>
                <template #dropdown>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="a">个人中心</el-dropdown-item>
                        <el-dropdown-item command="b" @click="LogOut">退出</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
    </div>
</template>

<script>
export default {
    methods: {
        LogOut() {
            this.$router.push('/')
        },
        handleMainPage(){
            this.$router.push('/main/products')
        }
    }
}
</script>

<style lang="less" scoped>
.header-container {
    padding: 0 20px;
    background-color: #333;
    height: 60px;
    display: flex;
    justify-content: space-between; //主轴上两端显示
    align-items: center; //纵轴上垂直居中

    .example-showcase .el-dropdown-link {
        cursor: pointer;
        color: var(--el-color-primary);
        display: flex;
        align-items: center;
    }

    .text {
        color: #fff;
        font-size: 14px;
        margin-left: 20px;
    }

    .r-content {
        .user {
            width: 40px;
            height: 40px;
            border-radius: 50%;
        }
    }
}
</style>