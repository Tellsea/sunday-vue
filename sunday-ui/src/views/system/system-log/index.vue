<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <el-collapse value="1">
      <el-collapse-item name="1">
        <el-form :inline="true" :model="searchForm" ref="searchForm">
          <el-form-item label="登录时间" prop="searchTime">
            <el-date-picker
              v-model="searchTime"
              type="daterange"
              unlink-panels
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              value-format="yyyy-MM-dd">
            </el-date-picker>
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
    <div class="button-container">
      <el-button type="danger" icon="el-icon-delete" @click="handleDeleteBatch">
        删除
      </el-button>
    </div>
    <!-- 数据表格 -->
    <el-table
      ref="table"
      :data="tableData"
      highlight-current-row
      :header-cell-style="{background:'#eef1f6', color:'#606266'}">
      <el-table-column
        type="selection"
        width="55"
        align="center">
      </el-table-column>
      <el-table-column
        label="编号">
        <template slot-scope="scope">
          <span>{{scope.row.id}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作人">
        <template slot-scope="scope">
          <span>{{scope.row.userName}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作描述">
        <template slot-scope="scope">
          <span>{{scope.row.operation}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="耗时(毫秒)">
        <template slot-scope="scope">
          <span>{{scope.row.time}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="IP">
        <template slot-scope="scope">
          <span>{{scope.row.ip}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作方法"
        show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{scope.row.method}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作参数"
        show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{scope.row.params}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作时间" width="160">
        <template slot-scope="scope">
          <span>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作地点">
        <template slot-scope="scope">
          <span>{{scope.row.location}}</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" width="70" label="操作">
        <template slot-scope="scope">
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
  </div>
</template>

<script>
  export default {
    name: "systemLogList",
    data() {
      return {
        searchTime: [],
        searchForm: {
          searchTime: '',
          page: 1,
          limit: 10
        },
        tableData: [],
        tableDataCount: 0,
        deleteVisible: false
      }
    },
    methods: {
      // 加载表格数据
      loadTable() {
        this.$api.systemLog.listByTable(this.searchForm).then(res => {
          this.tableData = res.data
          this.tableDataCount = res.count
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
        this.handlePagination({page: 0, limit: 10})
      },
      // 行删除
      handleDelete(row) {
        this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          this.executeDelete(row.id + '')
        })
      },
      // 批量删除
      handleDeleteBatch() {
        let selection = this.$refs.table.selection;
        if (selection.length === 0) {
          this.$message.error('请选择行')
          return false
        }
        this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          let param = [];
          for (let i = 0; i < selection.length; i++) {
            param.push(selection[i].id);
          }
          this.executeDelete(param.join(','))
        })
      },
      // 执行删除
      executeDelete(ids) {
        this.$api.systemLog.deleteByIds({ids: ids}).then(res => {
          this.$message.success({
            message: res.message,
            onClose: () => {
              this.loadTable()
            }
          })
        })
      }
    },
    mounted() {
      this.loadTable()
    }
  }
</script>

<style scoped>

</style>
