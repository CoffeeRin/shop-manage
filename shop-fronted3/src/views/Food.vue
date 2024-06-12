<template>
  <div class="product-manage">
    <el-card class="product-card">
      <div class="header">
        <h2>商品管理</h2>
        <div>
          <el-input v-model="searchName" class="search" placeholder="搜索" :prefix-icon="Search"
            @input="findProductByName" />
          <el-button type="primary" @click="openDialog">添加商品</el-button>
        </div>
      </div>
      <el-table :data="products" height="750" stripe>
        <el-table-column prop="id" label="ID" width="50" align="center"></el-table-column>
        <el-table-column prop="name" label="名称" align="center"></el-table-column>
        <el-table-column prop="categoryId" label="分类ID" align="center"></el-table-column>
        <el-table-column prop="price" label="价格" align="center"></el-table-column>
        <el-table-column prop="imageUrl" label="图片" width="180" align="center">
          <template #default="scope">
            <img :src="scope.row.imageUrl" alt="商品图片" class="product-image" />
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" align="center"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" align="center"></el-table-column>
        <el-table-column prop="updateTime" label="更新时间" align="center"></el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <template #default="scope">
            <el-button size="small" @click="editProduct(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="deleteProduct(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialogVisible" title="商品信息">
      <el-form :model="productForm" ref="productForm" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="productForm.name"></el-input>
        </el-form-item>
        <el-form-item label="分类ID" prop="categoryId">
          <el-input v-model="productForm.categoryId"></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="productForm.price"></el-input>
        </el-form-item>
        <el-form-item label="图片" prop="imageUrl">
          <el-input v-model="productForm.imageUrl"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-input v-model="productForm.status"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveProduct">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios';
import { ElMessage } from 'element-plus';

export default {
  data() {
    return {
      products: [],
      dialogVisible: false,
      productForm: {
        id: '',
        categoryId: '',
        name: '',
        price: '',
        imageUrl: '',
        status: ''
      },
      searchName: '',//搜索的商品名
      rules: {
        name: [{ required: true, message: '请输入名称', trigger: 'blur' }],
        categoryId: [{ required: true, message: '请输入分类ID', trigger: 'blur' }],
        price: [{ required: true, message: '请输入价格', trigger: 'blur' }],
        imageUrl: [{ required: true, message: '请输入图片URL', trigger: 'blur' }],
        status: [{ required: true, message: '请输入状态', trigger: 'blur' }]
      }
    };
  },
  methods: {
    //获取所有商品
    async fetchProducts() {
      try {
        const response = await axios.get('http://localhost:8089/api/products');
        this.products = response.data;
      } catch (error) {
        ElMessage.error('获取商品列表失败');
      }
    },
    //搜索商品
    async findProductByName() {
      try {
        const response = await axios.get(`http://localhost:8089/api/products/${this.searchName}`);
        this.products = response.data; //后端返回的也应是个数组
      } catch (error) {
        ElMessage.error('搜索商品失败');
      }
    },
    //打开对话框
    openDialog(product = null) {
      if (product) {
        this.productForm = { ...product };
      } else {
        this.productForm = {
          id: '',
          categoryId: '',
          name: '',
          price: '',
          imageUrl: '',
          status: ''
        };
      }
      this.dialogVisible = true;
    },
    //保存商品
    async saveProduct() {
      this.$refs.productForm.validate(async (valid) => {
        if (valid) {
          try {
            if (this.productForm.id) {
              await axios.put(`http://localhost:8089/api/products/${this.productForm.id}`, this.productForm);
              ElMessage.success('更新成功');
            } else {
              await axios.post('http://localhost:8089/api/products', this.productForm);
              ElMessage.success('添加成功');
            }
            this.dialogVisible = false;
            this.fetchProducts();
          } catch (error) {
            ElMessage.error('保存失败');
          }
        }
      });
    },
    //删除商品
    async deleteProduct(id) {
      try {
        await axios.delete(`http://localhost:8089/api/products/${id}`);
        ElMessage.success('删除成功');
        this.fetchProducts();
      } catch (error) {
        ElMessage.error('删除失败');
      }
    },
    //编辑
    editProduct(product) {
      this.openDialog(product);
    }
  },
  mounted() {
    this.fetchProducts();
  }
};
</script>

<style lang="less" scoped>
.product-manage {
  padding: 20px;
}

.product-card {
  width: 100%;

  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
  }

  .search {
    width: 240px;
    margin-right: 20px;
  }
}

.product-image {
  width: 100px;
  height: 100px;
  object-fit: cover;
}

.dialog-footer {
  text-align: right;
}
</style>
