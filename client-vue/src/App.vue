<script lang="ts">

import axios from 'axios';

export default {
  data() {
    return {
    
     items:[],
    };
  }
  ,

  methods: {
      async fechItems() {
      await axios.get( 'http://localhost:8080/api/items')
        .then(response => {
          this.items = response.data;
        })
        .catch(error => {
          console.error('There was an error fetching the categories :', error);
        });
    },
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
</template>

<style scoped>

</style>
