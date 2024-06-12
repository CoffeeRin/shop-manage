<template>
    <el-menu active-text-color="#ffd04b" background-color="#545c64" class="el-menu-vertical-demo" default-active="1"
        text-color="#fff">
        <h3 class="title">商品后台管理系统</h3>
        <el-menu-item index="1"  @click="handleProduct">
            <el-icon><Goods /></el-icon>
            <span>商品</span>
        </el-menu-item>
        <el-menu-item index="2"  @click="handleCategory">
            <el-icon><List /></el-icon>
            <span>分类统计</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
import { Goods, List } from '@element-plus/icons-vue';

export default {
    data() {
        return {
            
        }
    },
    components: {
        Goods,
        List
    },
    methods:{
        handleProduct(){
            this.$router.push('/main/products')
        },
        handleCategory(){
            this.$router.push('/main/category')
        },
    }
}
</script>

<style lang="less" scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
}

.el-menu {
    .title{
        margin-top: 0;
        margin-bottom: 40px;
        padding: 20px;
    }
    height: 100vh;
    border-right: none;

    h3 {
        color: #fff;
        text-align: center;
        line-height: 48px;
        font-style: 16px;
        font-weight: 400px;
    }
}
</style>