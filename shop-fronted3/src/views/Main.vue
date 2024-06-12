<template>
    <div class="common-layout">
        <el-container>
            <el-aside width="200px">
                <common-aside />
            </el-aside>
            <el-container>
                <el-header>
                    <common-head />
                </el-header>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import CommonAside from '@/components/CommonAside.vue';
import CommonHead from '@/components/CommonHead.vue';

export default {
    data() {
        return {

        }
    },
    components: {
        CommonAside,
        CommonHead,
    }
}
</script>

<style lang="less" scoped>
.common-layout {
    display: flex;
    height: 100vh;

    .el-header {
        padding: 0;
    }

    .fixed-aside {
        position: fixed;
        height: 100%;
        background-color: #001529;
        /* 修改为侧边栏背景色 */
        overflow: auto;
    }

    .fixed-header {
        position: fixed;
        width: calc(100% - 200px);
        top: 0;
        z-index: 1000;
        background-color: #fff;
        /* 修改头部背景色 */
    }

    .main-content {
        margin-top: 64px;
        /* 确保内容不被固定头部遮挡 */
        padding: 20px;
        background-color: #f0f2f5;
        /* 修改内容背景色 */
        min-height: calc(100vh - 64px);
        /* 确保内容区域占满剩余空间 */
    }
}
</style>