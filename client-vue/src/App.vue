<script lang="ts">

import axios from 'axios';

export default {
  data() {
    return {
      description:'',
      items:[],
    };
  }
  ,

  methods: {
     fechItems() {
       axios.get('http://localhost:8080/api/items')
        .then(response => {
          this.items = response.data;
        })
        .catch(error => {
          console.error('There was an error fetching the categories :', error);
        });
    },
    handleSubmit() {

      axios.post('http://localhost:8080/api/todoscreate', {
        description: this.description
      },
      )
      .then(response => {

          const createdItem = response.data;
          this.description="";
          this.items.push(createdItem);
        })
        .catch(error => {
          console.log(error);
        });

      }
  },
  created() {
    this.fechItems();

  },
};
</script>

<template>
 My todo
 <table class="table is-fullwidth"  v-if=" items!=null&&items.length > 0">
        <thead  class="thead">
          <tr class="tr">
            <th class="th">id</th>
            <th class="th">description</th>
          </tr>
        </thead>
        <tbody class="tbody">
          <tr v-for="item in items" :key="item.id">
            
            <td>{{ item.id }}</td>
            <td>{{ item.description }}</td>
          </tr>
        </tbody>
      </table>

      <form @submit.prevent="handleSubmit">
        <label>
          Description :
            <input v-model="description" placeholder="description" required />
        </label>
     
      <button type="submit">Add task</button>
    </form>
</template>

<style scoped>

</style>
