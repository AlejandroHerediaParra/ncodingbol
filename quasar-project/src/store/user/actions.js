import axios from 'axios'

export async function fetchUser ({ commit }) {
  return await axios.post('http://localhost:9090/api/auth/signin',
    { email: 'alejandroheredia777@gmail.com', password: 'alej' }).then(({ data }) => {
    commit('setUser', data)
  })
}
