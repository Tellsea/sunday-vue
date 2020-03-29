<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <el-collapse value="1">
      <el-collapse-item title="" name="1">
        <el-form :inline="true" :model="searchForm" ref="searchForm">
          <el-form-item label="用户名" prop="userName">
            <el-input v-model="searchForm.userName" placeholder="请输入"></el-input>
          </el-form-item>
          <el-form-item label="昵称" prop="nickName">
            <el-input v-model="searchForm.nickName" placeholder="请输入"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="phone">
            <el-input v-model="searchForm.phone" placeholder="请输入"></el-input>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select v-model="searchForm.status" placeholder="请选择">
              <el-option label="请选择" value="0"></el-option>
              <el-option label="正常" value="1"></el-option>
              <el-option label="锁定" value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="loadTable" title="查询">
              查询
            </el-button>
            <el-button icon="el-icon-refresh-left" @click="handleReset" title="重置">
              重置
            </el-button>
          </el-form-item>
        </el-form>
      </el-collapse-item>
    </el-collapse>
    <!-- 操作按钮 -->
    <div class="button-container" ref="buttonContainer">
      <el-button type="primary" title="新增" icon="el-icon-plus" @click="handleAdd">新增</el-button>
    </div>
    <!-- 数据表格 -->
    <el-table
      :data="tableData"
      :height="tableHeight"
      :highlight-current-row="true"
      :header-cell-style="{background:'#eef1f6', color:'#606266'}">
      <el-table-column
        label="序号"
        type="index"
        width="50">
      </el-table-column>
      <el-table-column
        label="头像">
        <template slot-scope="scope">
          <el-avatar shape="square" fit="fill" :src="scope.row.avatar"></el-avatar>
        </template>
      </el-table-column>
      <el-table-column
        label="用户名">
        <template slot-scope="scope">
          <p>{{scope.row.userName}}</p>
        </template>
      </el-table-column>
      <el-table-column
        label="昵称">
        <template slot-scope="scope">
          <p>{{scope.row.nickName}}</p>
        </template>
      </el-table-column>
      <el-table-column
        label="性别">
        <template slot-scope="scope">
          <div v-if="scope.row.sex == 0">保密</div>
          <div v-if="scope.row.sex == 1">男</div>
          <div v-if="scope.row.sex == 2">女</div>
        </template>
      </el-table-column>
      <el-table-column
        label="角色">
        <template slot-scope="scope">
          <p>{{scope.row.roleName}}</p>
        </template>
      </el-table-column>
      <el-table-column
        label="电话" width="100">
        <template slot-scope="scope">
          <p>{{scope.row.phone}}</p>
        </template>
      </el-table-column>
      <el-table-column
        label="登录次数">
        <template slot-scope="scope">
          <p>{{scope.row.loginTimes}}</p>
        </template>
      </el-table-column>
      <el-table-column
        label="最近登录时间" width="140">
        <template slot-scope="scope">
          <p>{{scope.row.lastLoginTime}}</p>
        </template>
      </el-table-column>
      <el-table-column
        label="创建人">
        <template slot-scope="scope">
          <p>{{scope.row.createUserName}}</p>
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间" width="140">
        <template slot-scope="scope">
          <p>{{scope.row.createTime}}</p>
        </template>
      </el-table-column>
      <el-table-column
        label="状态">
        <template slot-scope="scope">
          <el-tag type="success" effect="dark" v-if="scope.row.status == 1">正常</el-tag>
          <el-tag type="warning" effect="dark" v-if="scope.row.status == 2">锁定</el-tag>
          <el-tag type="danger" effect="dark" v-if="scope.row.status == 3">删除</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" fixed="right" width="200">
        <template slot-scope="scope">
          <el-button
            type="text"
            icon="el-icon-tickets"
            @click="handleShow(scope.row)">
            详情
          </el-button>
          <el-button
            type="text"
            icon="el-icon-edit"
            @click="handleEdit(scope.row)">
            编辑
          </el-button>
          <el-button
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页插件 -->
    <pagination v-if="tableDataCount > 0"
                ref="pagination"
                :total="tableDataCount"
                :page.sync="searchForm.page"
                :limit.sync="searchForm.limit"
                @pagination="handlePagination"/>
    <!-- 查看详情 -->
    <el-dialog
      title="查看用户信息"
      :visible.sync="showDialogVisible"
      width="800px">
      <el-form :model="showForm" ref="showForm" label-width="100px"
               class="demo-ruleForm">
        <el-col :span="12">
          <el-form-item label="头像" prop="userName">
            <el-avatar shape="square" :size="100" :src="showForm.avatar"></el-avatar>
          </el-form-item>
          <el-form-item label="用户名" prop="userName">
            <span>{{showForm.userName}}</span>
          </el-form-item>
          <el-form-item label="昵称" prop="nickName">
            <span>{{showForm.nickName}}</span>
          </el-form-item>
          <el-form-item label="角色" prop="roleName">
            <span>{{showForm.roleName}}</span>
          </el-form-item>
          <el-form-item label="电话" prop="phone">
            <span>{{showForm.phone}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="性别" prop="sex">
            <div v-if="showForm.sex === 0">保密</div>
            <div v-else-if="showForm.sex === 1">男</div>
            <div v-else-if="showForm.sex === 2">女</div>
          </el-form-item>
          <el-form-item label="描述" prop="description">
            <span>{{showForm.description}}</span>
          </el-form-item>
          <el-form-item label="登录次数" prop="loginTimes">
            <span>{{showForm.loginTimes}}</span>
          </el-form-item>
          <el-form-item label="最后登录时间" prop="lastLoginTime">
            <span>{{showForm.lastLoginTime}}</span>
          </el-form-item>
          <el-form-item label="创建人" prop="createUserName">
            <span>{{showForm.createUserName}}</span>
          </el-form-item>
          <el-form-item label="创建时间" prop="createTime">
            <span>{{showForm.createTime}}</span>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <div v-if="showForm.status === 1">
              <el-tag type="success">正常</el-tag>
            </div>
            <div v-else-if="showForm.status === 2">
              <el-tag type="warning">锁定</el-tag>
            </div>
          </el-form-item>
        </el-col>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="showDialogVisible=false">关闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "index",
    data() {
      return {
        searchForm: {
          userName: '',
          nickName: '',
          phone: '',
          status: '0',
          page: 0,
          limit: 10
        },
        tableData: [],
        tableHeight: 0,
        tableDataCount: 0,
        showForm: {},
        showDialogVisible: false,
        deleteVisible: false,
        add: {
          visiable: false
        }
      }
    },
    methods: {
      // 加载表格
      loadTable() {
        this.$api.userInfo.listByTable(this.searchForm).then(res => {
          this.tableDataCount = res.count
          this.tableData = res.data
        })
      },
      // 分页
      handlePagination(data) {
        this.searchForm.page = data.page
        this.searchForm.limit = data.limit
        this.loadTable()
      },
      // 重置
      handleReset() {
        this.$refs['searchForm'].resetFields()
        this.searchForm.status = '0'
        this.handlePagination({page: 0, limit: 10})
      },
      // 新增
      handleAdd() {
        this.$router.push({path: '/system/user-info/add'})
      },
      // 查看
      handleShow(row) {
        this.showDialogVisible = true
        this.showForm = row
      },
      // 编辑
      handleEdit(row) {
        this.$router.push({path: '/system/user-info/update', query: {id: row.id}})
      },
      // 删除
      handleDelete(row) {
        this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          let param = {
            id: row.id,
            status: 3
          }
          this.$api.userInfo.updateStatus(param).then(res => {
            this.$message.success({
              message: res.data.message,
              onClose: () => {
                this.loadTable()
              }
            })
          })
        })
      }
    },
    mounted() {
      this.loadTable()
      this.$nextTick(() => {
        let clientHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
        let collapseHeight = this.$refs.searchForm.$el.offsetHeight
        let buttonHeight = this.$refs.buttonContainer.offsetHeight
        // let paginationHeight = this.$refs.pagination.offsetHeight
        // console.log(paginationHeight)
        // 表格高度 = 窗口高度 - 头部高度 - 搜索条件高度 - 按钮行高度 - 上下边距 - 分页高度
        this.tableHeight = clientHeight - 84 - collapseHeight - buttonHeight - 20 - 42 - 50
      })
    }
  }
</script>

<style scoped>

</style>
