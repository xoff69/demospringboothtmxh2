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
      async fechItems() {
      await axios.get( 'http://localhost:8080/api/items')
        .then(response => {
          this.items = response.data;
        })
        .catch(error => {
          console.error('There was an error fetching the categories :', error);
        });
    },
    handleSubmit() {
        // Traiter les données du formulaire ici
        console.log(`description: ${this.description}`);

        axios.post('http://localhost:8080/api/todoscreate', {
          params: { description: this.description} ,
  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
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
       
        <span>
            Description : {{ description }} <br />
        </span>
      <button type="submit">Add task</button>
    </form>
</template>

<style scoped>

</style>
