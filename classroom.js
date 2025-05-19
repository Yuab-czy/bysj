import { post } from '@/utils/request'

export default {
  list: query => post('/api/admin/education/classroom/list'),
  pageList: query => post('/api/admin/education/classroom/page', query),
  edit: query => post('/api/admin/education/classroom/edit', query),
  select: id => post('/api/admin/education/classroom/select/' + id),
  deleteClassroom: id => post('/api/admin/education/classroom/delete/' + id)
}
