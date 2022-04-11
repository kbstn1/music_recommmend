// 匹配规则
const rules = {
  username: [
    { required: true, trigger: 'blur' }
  ],
  password: [
    { required: true, trigger: 'blur' }
  ],
  sex: [
    { required: true, message: '请选择性别', trigger: 'change' }
  ],
  phoneNum: [
    { essage: '请选择日期', trigger: 'blur' }
  ],
  email: [
    { message: '请输入邮箱地址', trigger: 'blur' },
    { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
  ],
  birth: [
    { type: 'date', message: '请选择日期', trigger: 'change' }
  ],
  introduction: [
    { message: '请输入介绍', trigger: 'blur' }
  ]
}

export {
  rules
}