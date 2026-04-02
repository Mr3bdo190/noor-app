import 'package:flutter/material.dart';

class QuranScreen extends StatelessWidget {
  const QuranScreen({super.key});

  final List<Map<String, dynamic>> _surahs = const [
    {'id': 1, 'name': 'الفاتحة', 'verses': 7, 'type': 'مكية'},
    {'id': 2, 'name': 'البقرة', 'verses': 286, 'type': 'مدنية'},
    {'id': 3, 'name': 'آل عمران', 'verses': 200, 'type': 'مدنية'},
    {'id': 4, 'name': 'النساء', 'verses': 176, 'type': 'مدنية'},
    {'id': 5, 'name': 'المائدة', 'verses': 120, 'type': 'مدنية'},
    {'id': 18, 'name': 'الكهف', 'verses': 110, 'type': 'مكية'},
    {'id': 36, 'name': 'يس', 'verses': 83, 'type': 'مكية'},
    {'id': 67, 'name': 'الملك', 'verses': 30, 'type': 'مكية'},
  ];

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      padding: const EdgeInsets.only(top: 60, left: 20, right: 20, bottom: 20),
      itemCount: _surahs.length + 1,
      itemBuilder: (context, index) {
        if (index == 0) {
          return const Padding(
            padding: EdgeInsets.only(bottom: 20),
            child: Text('المصحف الشريف', style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold, color: Color(0xFF38BDF8))),
          );
        }
        final surah = _surahs[index - 1];
        return Card(
          color: const Color(0xFF1E293B),
          margin: const EdgeInsets.only(bottom: 12),
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(15)),
          child: ListTile(
            contentPadding: const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
            leading: CircleAvatar(
              backgroundColor: const Color(0xFF38BDF8).withOpacity(0.2),
              child: Text('${surah['id']}', style: const TextStyle(color: Color(0xFF38BDF8), fontWeight: FontWeight.bold)),
            ),
            title: Text(surah['name'], style: const TextStyle(color: Colors.white, fontSize: 20, fontWeight: FontWeight.bold)),
            subtitle: Text('${surah['type']} • آياتها ${surah['verses']}', style: const TextStyle(color: Colors.grey)),
            trailing: const Icon(Icons.arrow_forward_ios, color: Colors.grey, size: 16),
            onTap: () {
              ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text('سيتم فتح صفحات السورة هنا')));
            },
          ),
        );
      },
    );
  }
}
