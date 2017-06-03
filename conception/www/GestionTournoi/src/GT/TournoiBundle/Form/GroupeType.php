<?php

namespace GT\TournoiBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class GroupeType extends AbstractType {

    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options) {

        $builder
        ->add('nom')
        ->add('numero')
        ->add('phase')
        ->add('equipes', EntityType::class, array(
            'class' => 'TournoiBundle:Equipe',
            'choice_label' => 'nom',
            'multiple' => true
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver) {
        $resolver->setDefaults(array(
            'data_class' => 'GT\TournoiBundle\Entity\Groupe'));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix() {
        return 'gt_tournoibundle_groupe';
    }

}
