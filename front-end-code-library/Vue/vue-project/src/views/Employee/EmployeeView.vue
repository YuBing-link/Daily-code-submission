<template>
  <div>
    <el-container style="height: 700px; border: 1px solid #eee">
      <el-header style="font-size: 40px; background-color: rgb(238, 241, 246)">用户管理系统</el-header>
      <el-container>
        <el-aside width="200px" style="border: 1px solid #eee;">
          <el-menu :default-openeds="['1', '3']">
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-message"></i>管理系统</template>
              <el-menu-item-group>

                <el-menu-item index="1-1">
                  <router-link to="/ele">员工管理</router-link>
                </el-menu-item>
                <el-menu-item index="1-2">
                  <router-link to="/emp">学生信息</router-link>
                </el-menu-item>

              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <!-- 表单 -->
          <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="姓名">
              <el-input v-model="formInline.name" placeholder="姓名"></el-input>
            </el-form-item>
            <el-form-item label="性别">
              <el-select v-model="formInline.gender" placeholder="性别">
                <el-option label="男" value="1"></el-option>
                <el-option label="女" value="2"></el-option>
              </el-select>
            </el-form-item>

            <span class="demonstration">入职日期</span>
            <el-date-picker v-model="formInline.hireDate" type="daterange" range-separator="至" start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>

            <el-form-item>
              <el-button type="primary" @click="onSubmit">查询</el-button>
            </el-form-item>
          </el-form>
          <!-- 表格 -->
          <el-table :data="tableData" border="true" style="width: 100%">
            <el-table-column prop="date" label="日期" width="120">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="image" label="图像" width="120">
              <template slot-scope="scope">
                <img :src="scope.row.image"  style="width: 100%; height: auto;">
              </template>
            </el-table-column>
            <el-table-column  label="性别" width="120">
              <template slot-scope="scope">
                {{scope.row.gender === '1' ? '男' : '女'}}
              </template>
            </el-table-column>
            <el-table-column prop="position" label="职位" width="120">
            </el-table-column>
            <el-table-column prop="hireDate" label="入职时间" width="120">
            </el-table-column>
            <el-table-column prop="lastOperation" label="最后操作" width="120">
            </el-table-column>
            <el-table-column prop="Operation" label="操作" width="120">
            </el-table-column>
          </el-table>
          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
            :current-page="currentPage4" :page-sizes="[100, 200, 300, 400]" :page-size="1"
            layout="total, sizes, prev, pager, next, jumper" :total="400">
          </el-pagination>
        </el-main>
      </el-container>
    </el-container>

  </div>
</template>

<script>

import axios from 'axios';  

export default {
  data() {
    return {
      tableData: [
        {
          date: '2016-05-03',
          name: '王小明',
          image: 'https://example.com/image1.jpg',
          gender: '1',
          position: '软件工程师',
          hireDate: '2016-05-03',
          lastOperation: '无',
          Operation: '编辑'
        },
        {
          date: '2016-05-04',
          name: '李四',
          image: 'https://example.com/image2.jpg',
          gender: '1',
          position: '产品经理',
          hireDate: '2016-05-04',
          lastOperation: '无',
          Operation: '编辑'
        },
        {
          date: '2016-05-05',
          name: '张三',
          image: 'https://example.com/image3.jpg',
          gender: '2',
          position: '测试工程师',
          hireDate: '2016-05-05',
          lastOperation: '无',
          Operation: '编辑'
        },

        {
          date: '2016-05-06',
          name: '赵六',
          image: 'https://example.com/image4.jpg',
          gender: '1',
          position: '产品经理',
          hireDate: '2016-05-06',
          lastOperation: '无',
          Operation: '编辑'
        }


      ],
      formInline: {
        name: '',
        gender: '',
        hireDate: ''
      },currentPage4: 1
    }
  },
  methods: {
    onSubmit: function () {
      // 处理查询逻辑
      alert('查询:' + JSON.stringify(this.formInline));
      // 在这里可以添加过滤逻辑来更新 tableData
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    
  },mounted() {
    // 在组件挂载时可以进行数据加载
    // 例如从服务器获取数据
    axios.get()
      .then(response => {
        this.tableData = response.data; // 假设返回的数据格式与 tableData 相同
      })
      .catch(error => {
        console.error('获取员工数据失败:', error);
      });
  }
}
</script>

<style></style>
