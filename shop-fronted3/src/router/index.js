import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: () => import('../views/Login.vue')
    },
    {
      path: '/main',
      name: 'main',
      component: () => import('../views/Main.vue'),
      children: [
        {
          path: 'products', // 修改为相对路径
          name: 'products',
          component: () => import('../views/Food.vue')
        },
        {
          path: 'category', // 修改为相对路径
          name: 'category',
          component: () => import('../views/Category.vue')
        }
      ]
    },
  ]
});

export default router;
