<template>
  <div class="table">
    <div class="container">
      <div class="handle-box">
        <el-button type="primary" size="mini" class="handle-del mr10" @click="delAll">批量删除</el-button>
        <el-input v-model="select_word" size="mini" placeholder="筛选关键词" class="handle-input mr10"></el-input>
        <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加歌曲</el-button>
      </div>
      <el-table :data="data" size="mini" border style="width: 100%" ref="multipleTable" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="40"></el-table-column>
        <el-table-column label="歌曲图片" width="102" align="center">
          <template slot-scope="scope">
            <div style="width: 80px; height: 80px; overflow: hidden; display:relative">
              <img :src="getUrl(scope.row.pic)" alt="" style="width: 100%;"/>
              <div class="play" @click="setSongUrl(scope.row.url, scope.row.name)">
                <div v-if="toggle !== scope.row.name">
                  <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-bofanganniu"></use>
                  </svg>
                </div>
                <div v-if="toggle === scope.row.name">
                  <svg class="icon" aria-hidden="true">
                    <use :xlink:href="playIcon"></use>
                  </svg>
                </div>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="歌名" prop="name" width="100" align="center"></el-table-column>
        <el-table-column label="歌手" prop="singerName" width="100" align="center"></el-table-column>
        <el-table-column label="简介" prop="introduction" align="center"></el-table-column>
        <el-table-column label="资源更新" width="100" align="center">
          <template slot-scope="scope">
            <el-upload
              class="upload-demo"
              :action="uploadUrl(scope.row.id)"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
              >
                <el-button size="mini">更新图片</el-button>
            </el-upload>
            <el-upload
              class="upload-demo change"
              :action="uploadSongUrl(scope.row.id)"
              :show-file-list="false"
              :on-success="handleSongSuccess"
              :before-upload="beforeSongUpload">
              <el-button size="mini">更新歌曲</el-button>
            </el-upload>
          </template>
        </el-table-column>
        <el-table-column label="评论" width="80" align="center">
            <template  slot-scope="scope">
                <el-button size="mini" @click="getComment(data[scope.$index].id)">评论</el-button>
            </template>
        </el-table-column>
        <el-table-column label="操作" width="150" align="center">
            <template slot-scope="scope">
                <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
            </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
          @current-change="handleCurrentChange"
          background
          layout="total, prev, pager, next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="tableData.length">
        </el-pagination>
      </div>
    </div>

    <!--添加歌曲-->
    <el-dialog title="添加歌曲" :visible.sync="centerDialogVisible" width="400px" center>
      <el-form ref="addSong" :model="registerForm" :rules="addSongRules" label-width="100px">
        <el-form-item label="歌曲名" size="mini" prop="name">
          <el-input v-model="registerForm.name"></el-input>
        </el-form-item>
        <el-form-item label="歌手名" size="mini" prop="singerName">
          <el-input v-model="registerForm.singerName"></el-input>
        </el-form-item>
        <el-form-item label="简介" size="mini" prop="introduction">
          <el-input  type="textarea" v-model="registerForm.introduction"></el-input>
        </el-form-item>
        <el-form-item label="上传歌曲">
          <el-upload accept=".mp3" style="display:inline-block;vertical-align: top;" ref="uploadAdd" action="" :auto-upload="false" :http-request="addSong" :limit="1" :before-upload="beforeSongUpload">
            <el-button size="small" type="success">请选择文件</el-button>
          </el-upload> 
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveAdd">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑弹出框 -->
    <el-dialog title="编辑" :visible.sync="editVisible" width="400px" center>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="歌曲名" size="mini">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="歌手名" size="mini">
          <el-input v-model="form.singerName"></el-input>
        </el-form-item>
        <el-form-item label="简介" size="mini">
          <el-input  type="textarea" v-model="form.introduction"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="editVisible = false">取 消</el-button>
        <el-button type="primary" size="mini" @click="saveEdit">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 删除提示框 -->
    <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
      <div class="del-dialog-cnt" align="center">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="delVisible = false">取 消</el-button>
        <el-button type="primary" size="mini" @click="deleteRow">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import SongAudio from '../components/SongAudio'
import { mixin } from '../mixins'
import { mapGetters } from 'vuex'
import '@/assets/js/iconfont.js'
import { getAllSong, updateSongMsg, deleteSong, setSong } from '../api/index'

export default {
  name: 'song-page',
  components: {
    SongAudio
  },
  mixins: [mixin],
  data () {
    return {
      toggle: false, // 控制播放图标状态
      registerForm: {
        name: '',
        singerName: '',
        introduction: ''
      },
      tableData: [],
      tempDate: [],
      is_search: false,
      multipleSelection: [], // 记录要删除的歌曲
      centerDialogVisible: false,
      editVisible: false,
      delVisible: false,
      select_word: '',
      form: {
        id: '',
        singerName: '',
        name: '',
        introduction: '',
        createTime: '',
        updateTime: '',
        pic: '',
        url: ''
      },
      addSongRules:{
        name:   [{ required: true, message:  "请输入歌曲名", trigger: "blur" }],
        singerName:   [{ required: true, message:  "请输入歌手名", trigger: "blur" }],
        introduction:   [{ required: true, message:  "请输入简介", trigger: "blur" }]
      },
      pageSize: 5, // 页数
      currentPage: 1, // 当前页
      idx: -1
    }
  },
  computed: {
    ...mapGetters([
      'isPlay' // 播放状态
    ]),
    // 计算当前表格中的数据
    data () {
      return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    },
    playIcon () {
      if (this.isPlay) {
        return '#icon-zanting'
      } else {
        return '#icon-bofanganniu'
      }
    }
  },
  watch: {
    select_word: function () {
      if (this.select_word === '') {
        this.tableData = this.tempDate
      } else {
        this.tableData = []
        for (let item of this.tempDate) {
          if (item.name.includes(this.select_word)||item.singerName.includes(this.select_word)) {
            this.tableData.push(item)
          }
        }
      }
    }
  },
  created () {
    this.getData()
  },
  destroyed () {
    this.$store.commit('setIsPlay', false)
  },
  methods: {
    // 获取歌曲
    getData () {
      getAllSong().then(res=>{
        this.tableData = res
        this.tempDate = res
      }).catch(err=>{
        this.notify('获取歌曲失败', 'error')
      })
    },
    setSongUrl (url, name) {
      this.$store.commit('setUrl', this.$store.state.HOST + url)
      this.toggle = name
      if (this.isPlay) {
        this.$store.commit('setIsPlay', false)
      } else {
        this.$store.commit('setIsPlay', true)
      }
    },
    // 更新歌曲图片
    uploadUrl (id) {
      return `${this.$store.state.HOST}/song/img/update?id=${id}`
    },
    // 更新歌曲url
    uploadSongUrl (id) {
      return `${this.$store.state.HOST}/song/url/update?id=${id}`
    },
    beforeSongUpload (file) {
      var testmsg = file.name.substring(file.name.lastIndexOf('.') + 1)
      const extension = testmsg === 'mp3'
      if (!extension) {
        this.$message({
          message: '上传文件只能是mp3格式！',
          type: 'error'
        })
      }
      return extension
    },
    // 获取当前页
    handleCurrentChange (val) {
      this.currentPage = val
    },
    handleSongSuccess (res, file) {
      if (res.code === 1) {
        this.getData()
        this.notify('上传成功', 'success')
      } else {
        this.notify('上传失败', 'error')
      }
    },
    // 添加音乐
    addSong (f) {
      if(!f) {
        this.notify('请上传歌曲文件','error')
        return
      }
      let param = new FormData()
      param.append('file',f.file)
      param.append('name',this.registerForm.name)
      param.append('singerName',this.registerForm.singerName)
      param.append('introduction',this.registerForm.introduction)
      setSong(param)
      .then(res=>{
        this.notify('添加成功', 'success')
        this.centerDialogVisible = false
        this.registerForm.name = ''
        this.registerForm.singerName = ''
        this.registerForm.introduction = ''
        this.getData()
      })
      .catch(err=>{
        this.notify('添加失败', 'error')
      })
    },
    //保存添加歌曲
    saveAdd () {
      this.$refs["addSong"].validate(valid => {
        if (!valid) {
          return;
        }
        this.$refs.uploadAdd.submit();
      });
    },
    //删除上传的歌曲
    handleRemove(file, fileList) {
         // 更新缓存文件
         console.log('文件删除')
     },
    // 编辑
    handleEdit (row) {
      this.idx = row.id
      this.form = {
        id: row.id,
        singerName: row.singerName,
        name: row.name,
        introduction: row.introduction,
        createTime: row.createTime,
        updateTime: row.updateTime,
        pic: row.pic,
        url: row.url
      }
      this.editVisible = true
    },
    getComment (id) {
      this.$router.push({path: '/home/comment', query: {id}})
    },
    // 保存编辑
    saveEdit () {
      let params = new URLSearchParams()
      params.append('id', this.form.id)
      params.append('singerName', this.form.singerName)
      params.append('name', this.form.name)
      params.append('introduction', this.form.introduction)
      updateSongMsg(params)
        .then(res => {
          if (res) {
            this.getData()
            this.notify('编辑成功', 'success')
          } else {
            this.notify('编辑失败', 'error')
          }
        })
        .catch(err => {
          console.log(err)
        })
      this.editVisible = false
    },
    // 确定删除
    deleteRow () {
      deleteSong(this.idx)
        .then(response => {
          if (response) {
            this.getData()
            this.notify('删除成功', 'success')
          } else {
            this.notify('删除失败', 'error')
          }
        })
        .catch(err => {
          console.log(err)
        })
      this.delVisible = false
    }
  }
}

</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}
.handle-input {
    width: 300px;
    display: inline-block;
}
.el-input__inner{
  background-color: aqua
}
.play {
    position: absolute;
    z-index: 100;
    width: 80px;
    height: 80px;
    top: 6px;
    left: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
}
.icon {
  width: 2em;
  height: 2em;
  color: white;
  fill: currentColor;
  overflow: hidden;
}
.pagination {
    display: flex;
    justify-content: center;
}
</style>
